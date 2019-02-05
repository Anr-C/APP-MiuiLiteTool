package com.lckiss.miuilitetool.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by root on 17-10-8.
 */

public class Utils {


    /**
     * isRooted
     *
     * @return boolean
     */
    public static boolean isRooted() {
        return shell("ls /data").size() != 0;
    }

    /**
     * isMounted
     *
     * @return boolean
     */
    public static boolean isMounted() {
        shell("mount -o rw,remount /system");
        return !shell("mount |grep system").contains("ro");
    }


    public static ArrayList<String> shell(String cmd) {
        Process process = null;
        DataOutputStream os = null;
        DataInputStream is = null;
        final String TAG = "V";
        ArrayList<String> res = new ArrayList();

        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            is = new DataInputStream(process.getInputStream());
            os.writeBytes(cmd + " \n");  //这里可以执行具有root 权限的程序了
            os.writeBytes(" exit \n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error - Here is what I know:" + e.getMessage());
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(is,
                                    "utf-8"));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        res.add(line);
                    }
                    is.close();
                    process.destroy();
                }
            } catch (Exception e) {
                Log.d(TAG, "shell: " + e);
            }
        }
        return res;
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    public static String getSystemVersionName() {
        ArrayList res=shell("getprop ro.miui.ui.version.name");
        if (res.size()!=0){
            return res.get(0).toString();
        }else {
            return null;
        }
    }
}
