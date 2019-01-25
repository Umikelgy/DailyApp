package com.example.a10068921.myapplication.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import com.example.a10068921.myapplication.R;

import java.util.Arrays;

import static com.example.a10068921.myapplication.common.SQLiteData.DBNAME;


/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/9 9:53
 **/
public class DatabaseHelper extends SQLiteOpenHelper {
    private String sql;
    private Context context;
/**
 *@description
 *     //参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类
 *@anthor  10068921
 */

    public DatabaseHelper(Context context , SQLiteDatabase.CursorFactory factory, int version, String sql) {
        super(context, DBNAME, factory, version);
        this.context=context;
        this.sql = sql;
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    /**
     *@description
     * 如果数据库不存在，就调用onCreate，只有初次生成数据库时才会被调用
     *@param
     *@return
     *@anthor  10068921
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//      Example: sql="CREATE TABLE IF NOT EXISTS person
// (personid integer primary key autoincrement, name varchar(20), age INTEGER)"
        String sql=context.getString(R.string.createEventTable);
        sqLiteDatabase.execSQL(sql);

      Arrays.asList(context.getResources().getStringArray(R.array.insert)).stream().forEach(insert->{
          sqLiteDatabase.execSQL(insert);
      });


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
