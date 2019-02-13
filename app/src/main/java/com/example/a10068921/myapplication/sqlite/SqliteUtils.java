package com.example.a10068921.myapplication.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.a10068921.myapplication.common.SQLiteData.VERSION;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/9 14:17
 **/
public class SqliteUtils  {
   private DatabaseHelper databaseHelper;
   private Context context;
   private  SQLiteDatabase db;
   private String sql;
    public SqliteUtils(Context context,String sql) {
        databaseHelper=new DatabaseHelper(context,null,VERSION,sql);
        db=databaseHelper.getWritableDatabase();
        this.context = context;
        this.sql=sql;
    }
/**
 * 导入数据库实例化
 * */
    public SqliteUtils(Context context,String dbName,int version) {

        databaseHelper=new DatabaseHelper(context,dbName,null,version);
        db=databaseHelper.getWritableDatabase();
    }
/**封装不同数据结构，返回SQLiteDatabase类型数据，重新实现所需数据结构*/
    public SQLiteDatabase dataType(){
        return databaseHelper.getReadableDatabase();
    }
    public void addTable(String sqlTable){
        db.execSQL(sql);
        sql=sqlTable;
    }
    public void inputDB(String sql){
        db.execSQL(sql);
    }
    public void modifyDB(String sqlTable){
       databaseHelper=new DatabaseHelper(context,null,db.getVersion()+1,sql);
       sql=sqlTable;
    }
    public void modifyTable(String sql){
        db.execSQL(sql);
    }

    public Map<String,Object> selectTableMassage(String[] keys){
         db=databaseHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,keys);
        Map<String,Object> resultMap=new HashMap<>(16);
        int columnNumber=cursor.getColumnCount();
        Object object;
        int y=0;
        while(cursor.moveToNext()){
            for(int i=0;i<columnNumber;i++){
                object=cursor.getString(i);
                resultMap.put(cursor.getColumnName(i)+y,object);
            }
            y++;
        }
        return resultMap;
    }
    public void insertTableMassage(Object[] objects){
        if (objects==null){
            db.execSQL(sql);
        }
        else {
            db.execSQL(sql, objects);
        }
    }
    public void deleteTableMassage(String table,String key,String value){
        String whereClause=key+"=?";
        String[] whereArgs=new String[]{String.valueOf(value)};
        db.delete(table,whereClause,whereArgs);

    }
    public void close(){
        if(db!=null){
            db.close();
        }
        if(databaseHelper!=null){
            databaseHelper.close();
        }
    }

}
