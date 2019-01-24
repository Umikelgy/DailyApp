package com.example.a10068921.myapplication.sqlite.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.a10068921.myapplication.sqlite.NormalModel;
import com.example.a10068921.myapplication.sqlite.SqliteService;
import com.example.a10068921.myapplication.sqlite.SqliteUtils;
import com.example.a10068921.myapplication.sqlite.TestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/21 15:26
 **/
public class SqliteServiceImpl implements SqliteService {
    @Override
    public List<NormalModel> selectNormalAllEvent(Context context) {
        String sql="select * from example1 limit 2,3";
        SqliteUtils sqliteUtils=new SqliteUtils(context,sql);
        return null;
    }

    @Override
    public List<TestModel> testAllExample(Context context) {
        String sql="select * from example1 limit 2,3";
        List<TestModel> result=new ArrayList<>();
        SqliteUtils sqliteUtils=new SqliteUtils(context,sql);
        SQLiteDatabase db=sqliteUtils.dataType();
        Cursor cursor=db.rawQuery(sql,null);
        while(cursor.moveToNext()){
//           result.add()
        }



        return null;
    }
}
