package com.example.a10068921.myapplication.sqlite.sqlthread;

import android.content.Context;
import com.example.a10068921.myapplication.sqlite.NormalModel;
import com.example.a10068921.myapplication.sqlite.SqliteUtils;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/29 10:31
 **/
public class DeleteThread implements Runnable {
    private NormalModel normalModel;
    private Context context;

    public DeleteThread(NormalModel normalModel,Context context) {
        this.normalModel = normalModel;
        this.context=context;
    }

    @Override
    public void run() {
        SqliteUtils sqliteUtils=new SqliteUtils(context,null);
        sqliteUtils.deleteTableMassage("event_db","create_time",normalModel.getCreateTime());
    }
}
