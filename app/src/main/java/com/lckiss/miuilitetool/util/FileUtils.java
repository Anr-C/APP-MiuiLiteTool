package com.lckiss.miuilitetool.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by lckiss on 17-10-23.
 */

public class FileUtils {

    private Context context;
    private static final String FileName="clean_log";


    public FileUtils(Context context) {
        this.context = context;
    }

    /*
* 定义文件保存的方法，写入到文件中，所以是输出流
* */
    public void save(String content) throws Exception {
        //Context.MODE_APPEND，写入的内容不会覆盖文本内原有内容
        FileOutputStream output = context.openFileOutput(FileName, Context.MODE_APPEND);
        output.write(content.getBytes());  //将String字符串以字节流的形式写入到输出流中
        output.close();         //关闭输出流
    }


    /*
    * 定义文件读取的方法
    * */
    public String read() throws IOException {
        //打开文件输入流
        FileInputStream input = context.openFileInput(FileName);

        BufferedReader br= new BufferedReader(new FileReader(input.getFD()));
        String line;
        StringBuilder sb = new StringBuilder("");
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        //关闭输入流
        input.close();
        //返回字符串
        return sb.toString();
    }
}
