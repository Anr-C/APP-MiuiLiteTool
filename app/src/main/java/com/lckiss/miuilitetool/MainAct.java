package com.lckiss.miuilitetool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lckiss.miuilitetool.bean.BaseMobile;
import com.lckiss.miuilitetool.util.FileUtils;
import com.lckiss.miuilitetool.util.MobileUtils;
import com.lckiss.miuilitetool.util.Utils;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lckiss.miuilitetool.util.Utils.getSystemModel;
import static com.lckiss.miuilitetool.util.Utils.getSystemVersion;
import static com.lckiss.miuilitetool.util.Utils.getSystemVersionName;
import static com.lckiss.miuilitetool.util.Utils.shell;

public class MainAct extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @BindView(R.id.switchCompat)
    SwitchCompat switchCompat;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.rules)
    TextView rules;
    @BindView(R.id.menu)
    TextView menu;
    @BindView(R.id.status_root)
    ImageView statusRoot;
    @BindView(R.id.status_mount)
    ImageView statusMount;
    @BindView(R.id.status_search)
    ImageView statusSearch;
    @BindView(R.id.status_clean)
    ImageView statusClean;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.log)
    TextView log;
    @BindView(R.id.redo)
    TextView redo;

    /**
     * 判断是否root的标志位
     */
    private boolean isRoot;
    /**
     * 判断button的点击
     */
    private boolean isFirst = true;
    private Handler handler;
    private ExecutorService cachedThreadPool;
    private SharedPreferences sp;
    private FileUtils fileUtils;
    /**
     * 存放扫描和删除结果
     */
    private StringBuilder stringBuilder;

    private static final String TAG = "MainAct";

    /**
     * 容器
     */
    ArrayList<String> app;
    ArrayList<String> priApp;
    ArrayList<String> miuiAppList;
    ArrayList<String> miuiPriAppList;
    ArrayList<String> delAppList;
    ArrayList<String> delPriAppList;
    String[] miuiApp;
    String[] miuiPriApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
        initFirst();
        initData();
        initView();
    }

    private void initFirst() {
        sp = getSharedPreferences("Setting", MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        if (isFirst) {
            String mobile = getSystemModel().toUpperCase();
            String mode = MobileUtils.getMode(mobile);
            if (mode == null) {
                mode = "Mi4cLite";
            }
            sp.edit().putString("liteMode", mode).apply();
            sp.edit().putBoolean("isFirst", false).apply();
        }
    }

    private void initData() {
        refreshMode();
        delAppList = new ArrayList<>();
        delPriAppList = new ArrayList<>();
    }

    private void refreshMode() {
        String liteMode = sp.getString("liteMode", "Mi4cLite");
        try {
            BaseMobile baseMobile = MobileFctory.getInstance("com.lckiss.miuilitetool.bean." + liteMode);
            miuiApp = baseMobile.getApp().split(",");
            miuiPriApp = baseMobile.getPriApp().split(",");
            rules.setText(String.format("当前选择的匹配规则版本：%s", baseMobile.getMode()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        miuiAppList = new ArrayList<>(Arrays.asList(miuiApp));
        miuiPriAppList = new ArrayList<>(Arrays.asList(miuiPriApp));
    }

    private void initView() {
        mobile.setText(String.format("当前手机型号：%s 版本:%s%s", getSystemModel().toUpperCase(), sp.getString("ROM", ""), getSystemVersion()));
        isRoot = sp.getBoolean("isRoot", false);
        switchCompat.setChecked(isRoot);

        fileUtils = new FileUtils(this);
        stringBuilder = new StringBuilder();
        cachedThreadPool = Executors.newCachedThreadPool();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                switch (msg.what) {
                    case 1:
                        boolean root = (boolean) msg.obj;
                        if (!root) {
                            errorDialog("您的设备没有ROOT或者您拒绝了ROOT申请,请重新授予!");
                        }
                        drawIcon(statusRoot, root);
                        break;
                    case 2:
                        boolean mount = (boolean) msg.obj;
                        drawIcon(statusMount, mount);
                        if (!mount) {
                            btnStatus(true, "重新扫描垃圾应用");
                        }
                        break;
                    case 3:
                        String search = (String) msg.obj;
                        drawIcon(statusSearch, true);
                        if ("clean".equals(search)) {
                            drawIcon(statusClean, true);
                        }
                        logDialog("扫描信息", stringBuilder.toString().trim());
                        saveLog();
                        break;
                    case 4:
                        String del = (String) msg.obj;
                        if ("ok".equals(del)) {
                            drawIcon(statusClean, true);
                            restartDialog();
                        } else {
                            drawIcon(statusClean, false);
                            logDialog("扫描信息", stringBuilder.toString().trim());
                        }
                        saveLog();
                        btnStatus(true, "重新扫描垃圾应用");
                        break;
                    case 5:
                        btnStatus(true, "重新扫描垃圾应用");
                        sp.edit().putBoolean("isRoot", isRoot).apply();
                        switchCompat.setChecked(isRoot);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    private void saveLog() {
        try {
            String res = stringBuilder.toString();
            fileUtils.save(res);
            //清空当前
            stringBuilder.setLength(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawIcon(ImageView v, boolean isOk) {
        if (isOk) {
            v.setImageDrawable(new IconicsDrawable(MainAct.this)
                    .icon(Ionicons.Icon.ion_ios_checkmark_outline)
                    .color(Color.parseColor("#424242"))
                    .sizeDp(16));
        } else {
            v.setImageDrawable(new IconicsDrawable(MainAct.this)
                    .icon(Ionicons.Icon.ion_ios_close_outline)
                    .color(Color.parseColor("#424242"))
                    .sizeDp(16));
        }

    }

    @OnClick({R.id.switchCompat, R.id.menu, R.id.button, R.id.log, R.id.redo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.switchCompat:
                isRoot = Utils.isRooted();
                if (!isRoot) {
                    errorDialog("您的设备没有ROOT或者您拒绝了ROOT申请,请重新授予!");
                }
                sp.edit().putBoolean("isRoot", isRoot).apply();
                switchCompat.setChecked(isRoot);
                break;
            case R.id.menu:
                // 创建PopupMenu对象
                PopupMenu popup = new PopupMenu(MainAct.this, menu);
                // 将R.menu.popup_menu菜单资源加载到popup菜单中
                getMenuInflater().inflate(R.menu.rules, popup.getMenu());
                // 为popup菜单的菜单项单击事件绑定事件监听器
                popup.setOnMenuItemClickListener(this);
                popup.show();
                break;
            case R.id.button:
                cachedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            doOperate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.redo:
                reInitBtn();
                break;
            case R.id.log:
                String fileContent = "";
                try {
                    fileContent = fileUtils.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if ("".equals(fileContent)) {
                    logDialog("扫描信息", "暂无相关日志！！！");
                } else {
                    logDialog("扫描信息", fileContent);
                }
                break;
            default:
                break;
        }
    }

    private void doOperate() {
        btnStatus(false, "正在检查所需环境");

        //第一阶段 root
        Message root = new Message();
        root.what = 1;
        root.obj = isRoot;
        handler.sendMessage(root);
        if (!isRoot) {
            return;
        }

        String ROM = getSystemVersionName();
        if (ROM == null) {
            errorDialog("您的手机系统不是MIUI,暂不支持谢谢！");
            btnStatus(true, "重新扫描垃圾应用");
            return;
        } else {
            sp.edit().putString("ROM", "MIUI " + ROM + " ").apply();
        }

        btnStatus(false, "正在挂载系统读写");
        //第二阶段 mount
        boolean isMount = Utils.isMounted();
        Message mount = new Message();
        mount.what = 2;
        mount.obj = isMount;
        handler.sendMessage(mount);
        if (!isMount) {
            return;
        }

        //点击逻辑
        if (isFirst) {
            doSearch();
        } else {
            isFirst = true;
            doDel();
        }

    }

    private void doSearch() {
        btnStatus(false, "正在扫描垃圾应用");
        //清除上次残留数据
        refreshData();

        //第三阶段 获取手机应用列表
        stringBuilder.append(Calendar.getInstance().getTime() + "\n位置：/system/app待删除列表：\n");

        for (String a : app) {
            if (!miuiAppList.contains(a)) {
                stringBuilder.append("  应用：" + a + "\n");
                delAppList.add(a);
            }
        }
        if (delAppList.size() == 0) {
            stringBuilder.append("  该目录暂无垃圾应用！\n");
        }

        stringBuilder.append("位置：/system/priv-app待删除列表：\n");
        for (String b : priApp) {
            if (!miuiPriAppList.contains(b)) {
                stringBuilder.append("  应用：" + b + " \n");
                delPriAppList.add(b);
            }
        }
        if (delPriAppList.size() == 0) {
            stringBuilder.append("  该目录暂无垃圾应用！\n");
        }

        Message search = new Message();
        search.what = 3;
        if (delAppList.size() == 0 && delPriAppList.size() == 0) {
            stringBuilder.append("您的系统非常干净！\n\n");
            search.obj = "clean";
            handler.sendMessage(search);
            btnStatus(true, "重新扫描垃圾应用");
            return;
        }

        btnStatus(true, "点击清理垃圾应用");
        isFirst = false;
        search.obj = "noClean";
        handler.sendMessage(search);
    }

    private void doDel() {
        btnStatus(false, "正在清理垃圾应用");
        int errorCount = 0;
        stringBuilder.append("\n位置：/system/app删除列表：\n");
        if (delAppList.size() == 0) {
            stringBuilder.append("  该目录暂无垃圾应用！\n");
        } else {
            for (String a : delAppList) {
                String cmd = "rm -r /system/app/" + a + "/";
                if (shell(cmd).size() != 0) {
                    stringBuilder.append("  应用：").append(a).append(" 删除失败 \n");
                    errorCount++;
                } else {
                    stringBuilder.append("  应用：").append(a).append(" 已删除 \n");
                }
            }
        }
        stringBuilder.append("位置：/system/priv-app删除列表：\n");
        if (delPriAppList.size() == 0) {
            stringBuilder.append("  该目录暂无垃圾应用！\n\n");
        } else {
            for (String b : delPriAppList) {
                String cmd = "rm -r /system/priv-app/" + b + "/";
                if (shell(cmd).size() != 0) {
                    stringBuilder.append("  应用：").append(b).append(" 删除失败 \n");
                    errorCount++;
                } else {
                    stringBuilder.append("  应用：").append(b).append(" 已删除 \n");
                }
            }
        }
        btnStatus(true, "重新扫描垃圾应用");
        Message del = new Message();
        del.what = 4;
        if (errorCount > 0) {
            del.obj = "error";
        } else {
            del.obj = "ok";
        }
        handler.sendMessage(del);
    }

    private void reInitBtn() {
        isFirst = true;
        btnStatus(true, "一键清理系统无用软件");
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        SharedPreferences.Editor editor = sp.edit();
        switch (item.getItemId()) {
            case R.id.mi4c_normal:
                editor.putString("liteMode", "Mi4cNormal").apply();
                reInitBtn();
                refreshMode();
                break;
            case R.id.mi4c_Lite:
                editor.putString("liteMode", "Mi4cLite").apply();
                reInitBtn();
                refreshMode();
                break;
            case R.id.mi5_normal:
                editor.putString("liteMode", "Mi5Normal").apply();
                reInitBtn();
                refreshMode();
                break;
            case R.id.mi5_Lite:
                editor.putString("liteMode", "Mi5Lite").apply();
                reInitBtn();
                refreshMode();
                break;
            case R.id.redmi_note2_normal:
                editor.putString("liteMode", "RedMi2Normal").apply();
                reInitBtn();
                refreshMode();
                break;
            case R.id.custom_rules:
                Toast.makeText(this, "抱歉暂时不支持自定义规则", Toast.LENGTH_SHORT).show();

                break;
            case R.id.logback:
                goBrowser("http://lckiss.com/?p=2246");
                break;
            case R.id.author_info:
                goBrowser("http://s.lckiss.com/");
                break;
            case R.id.author_blog:
                goBrowser("http://lckiss.com/");
                break;
            case R.id.donate:
                goBrowser("https://ds.alipay.com/?from=mobilecodec&scheme=alipays%3A%2F%2Fplatformapi%2Fstartapp%3FsaId%3D10000007%26clientVersion%3D3.7.0.0718%26qrcode%3Dhttps%253A%252F%252Fqr.alipay.com%252FFKX04528PJRFWQ9UWA2XD3%253F_s%253Dweb-other");
                break;
            default:
                break;
        }
        return false;
    }

    private void goBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private void logDialog(final String title, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MainAct.this)
                        .setTitle(title)
                        .setMessage(msg)
                        .setPositiveButton("好的，我知道了", null)
                        .create().show();
            }
        });
    }

    private void restartDialog() {
        new AlertDialog.Builder(this)
                .setTitle("是否重启以生效")
                .setMessage("如果出现应用停止运行情况，请立即重启！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shell("su -c \"/system/bin/reboot\"");
                    }
                }).setNegativeButton("取消", null).create().show();
    }

    private void errorDialog(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MainAct.this)
                        .setTitle("出错啦!!!")
                        .setMessage(msg)
                        .setPositiveButton("重新尝试", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cachedThreadPool.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (!isRoot) {
                                                    logDialog("授权结果", "您的系统未ROOT或者已拒绝");
                                                    handler.sendEmptyMessage(5);
                                                }
                                            }
                                        }, 7000);
                                        btnStatus(false, "正在申请ROOT权限");
                                        isRoot = Utils.isRooted();
                                        handler.sendEmptyMessage(5);
                                    }
                                });
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create().show();
            }
        });

    }

    private void refreshData() {
        if (!delAppList.isEmpty()) {
            delAppList.clear();
        }
        if (!delPriAppList.isEmpty()) {
            delPriAppList.clear();
        }
        app = shell("ls /system/app");
        priApp = shell("ls /system/priv-app");

    }

    private void btnStatus(final boolean status, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    button.setEnabled(status);
                    button.setText(msg);
                } catch (Exception e) {
                    Log.d(TAG, "btnStatus... runOnUiThread: " + e);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cachedThreadPool.shutdown();
    }

}
