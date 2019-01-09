package com.example.a10068921.myapplication.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/9 9:53
 **/
public class DatabaseHelper extends SQLiteOpenHelper {

    private String sql;
/**
 *@description
 *     //参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类
 *@anthor  10068921
 */

    public DatabaseHelper(Context context ,String dbName, SQLiteDatabase.CursorFactory factory, int version, String sql) {
        super(context, dbName, factory, version);
        this.sql = sql;
    }


    /**
     *@description
     * 如果数据库不存在，就调用onCreate，只有初次生成数据库时才会被调用
     *@param
     *@return
     *@anthor  10068921
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//      Example: sql="CREATE TABLE IF NOT EXISTS person
// (personid integer primary key autoincrement, name varchar(20), age INTEGER)"
        sqLiteDatabase.execSQL(sql);
    }

    /**
     *@description
     * 更新数据库版本时调用
     *@param
     *@return
     *@anthor  10068921
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL(sql);

    }



}
