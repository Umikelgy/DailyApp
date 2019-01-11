package com.example.a10068921.myapplication.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.a10068921.myapplication.common.SQLiteData.VERSION;


/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/11 8:51
 **/
public class InputSql {

    private SqliteUtils utils;
    public InputSql(Context context,String dbName) {
        utils=new SqliteUtils(context,dbName,VERSION);

    }

    public boolean sysnSql(InputStream inputStream){
        boolean flag=false;
        String sqlUpdate=null;
        try {
            sqlUpdate=readText(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String [] sqls=sqlUpdate.split(";");
       for(int i=0;i<sqls.length;i++){
           if(!TextUtils.isEmpty(sqls[i])){
               utils.inputDB(sqls[i]);
               flag=true;
           }
       }
       if(utils!=null){
           utils.close();
       }
       return flag;
    }
    private String readText(InputStream is) throws IOException {
        InputStreamReader reader=new InputStreamReader(is);
        BufferedReader br=new BufferedReader(reader);
        StringBuffer buffer=new StringBuffer();
        String str;
        while((str=br.readLine())!=null){
            buffer.append(str);
            buffer.append("\n");
        }
        br.close();
        reader.close();
        return buffer.toString();
    }
}
