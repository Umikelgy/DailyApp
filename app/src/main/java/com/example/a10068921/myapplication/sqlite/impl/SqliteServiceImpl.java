package com.example.a10068921.myapplication.sqlite.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import com.example.a10068921.myapplication.sqlite.NormalModel;
import com.example.a10068921.myapplication.sqlite.SqliteService;
import com.example.a10068921.myapplication.sqlite.SqliteUtils;
import com.example.a10068921.myapplication.sqlite.TestModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/21 15:26
 **/
public class SqliteServiceImpl implements SqliteService {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<NormalModel> selectNormalAllEvent(Context context,int tableIndex) {
        String sql="select * from event_db limit "+tableIndex+",10";
        SqliteUtils sqliteUtils=new SqliteUtils(context,sql);
        List<NormalModel> result=new ArrayList<>();
        SQLiteDatabase db=sqliteUtils.dataType();
        Cursor cursor= db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            result.add(NormalModel.newBuilder()
                    .name(cursor.getString(1))
                    .createTime(LocalDateTime.parse(cursor.getString(2)))
                    .description(cursor.getString(5))
                    .descriptionPath(cursor.getString(8))
                    .builder()
            );
        }
        return result;
    }

    @Override
    public List<TestModel> testAllExample(Context context) {
        String sql="select * from example1 limit 2,3";
        List<TestModel> result=new ArrayList<>();
        SqliteUtils sqliteUtils=new SqliteUtils(context,sql);
        SQLiteDatabase db=sqliteUtils.dataType();
        Cursor cursor=db.rawQuery(sql,null);
        while(cursor.moveToNext()){
           result.add(TestModel.newBuilder()
           .id(Integer.parseInt(cursor.getString(0)))
           .name(cursor.getString(1))
           .number(Integer.parseInt(cursor.getString(2)))
           .builder());
        }
        return result;
    }
}
