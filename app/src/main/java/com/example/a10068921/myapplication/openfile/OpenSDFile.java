package com.example.a10068921.myapplication.openfile;

import android.os.Environment;

import java.io.*;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/11 10:24
 **/
public class OpenSDFile {
    public void writeFile(String content)  {

        try {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File sdCardDir= Environment.getDataDirectory();
                File targetFile = new File(sdCardDir.getCanonicalPath() + "/TestFile.txt");
                //以指定文件创建RandomAccessFile对象
                RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
                //记住，输入的时候位置是要用到指针的
                raf.seek(targetFile.length());
                //输出文件内容
                raf.write(content.getBytes());
                //关闭RandomAccessFile
                raf.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public String read() {
        try {
            //这个判断语句使我们常用的：判断手机是否插入了SD卡和是否有访问权限，如果都有就返回true
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //后面的这个方法是返回SD卡中的目录
                /*File sdCardDir = Environment.getDataDirectory();
                String path=findDBFile(sdCardDir);*/
                //获取指定文件的输入流
                FileInputStream fis = new FileInputStream("/data/data/com.example.a10068921.myapplication/databases/eventdb");
                //将指定输入流包装成BufferedReader
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                StringBuilder sb = new StringBuilder();
                String line = null;
                //循环读取文件内容
                while ((line = br.readLine()) != null) {
                    line=new String (line.getBytes(),"gb2312");
                    sb.append(line);
                }
                fis.close();
                br.close();
                return sb.toString();
            }
            return "The SDCard is failed!!";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    private String findDBFile(File sdCardDir) {

        return null;
    }
}
