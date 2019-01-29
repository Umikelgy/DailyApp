package com.example.a10068921.myapplication.sqlite.sqlthread;

import android.content.Context;
import com.example.a10068921.myapplication.sqlite.NormalModel;
import com.example.a10068921.myapplication.sqlite.SqliteUtils;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/29 14:53
 **/
public class AddThread implements Runnable {
    private Context context;
    private NormalModel normalModel;

    public AddThread(Context context, NormalModel normalModel) {
        this.context = context;
        this.normalModel = normalModel;
    }

    @Override
    public void run() {
        String sql="insert into (name,create_time,update_time,description,description_path)values" +
                "('"+normalModel.getName()+"','"+normalModel.getCreateTime()+"','"+normalModel.getCreateTime()+"','"+normalModel.getDescription()
                +"','"+normalModel.getDescriptionPath()+"')";
       new SqliteUtils(context,sql).inputDB(sql);
    }
}
