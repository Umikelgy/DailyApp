package com.example.a10068921.myapplication.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.a10068921.myapplication.common.SQLiteData.DBNAME;
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

    public SqliteUtils(Context context) {
        this.context = context;
    }

    public void modifyDB(String sql, String dbName){
        int version=db.getVersion()+1;
        if (dbName==null||dbName==DBNAME){
           dbName=DBNAME;

        }else {
            version=VERSION;
        }
        databaseHelper=new DatabaseHelper(context,dbName,null,version,sql);
        databaseHelper.close();
    }
    public Map<String,Object> selectTableMassage(String sql,String[] keys){
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
        db.close();
        databaseHelper.close();
        return resultMap;
    }
    public void insertTableMassage(String sql,Object[] objects){
        db=databaseHelper.getWritableDatabase();
        if (objects==null){
            db.execSQL(sql);
        }
        else {
            db.execSQL(sql, objects);
        }

        db.close();
        databaseHelper.close();
    }
    public void deleteTableMassage(String table,String key,String value){
        String whereClause=key+"=?";
        String[] whereArgs=new String[]{String.valueOf(value)};
        db.delete(table,whereClause,whereArgs);
        db.close();
        databaseHelper.close();
    }

}
