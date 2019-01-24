package com.example.a10068921.myapplication.sqlite;

import android.content.Context;

import java.util.List;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/21 14:53
 **/
public interface SqliteService {
    /**
     * @return ：NormalModel
     * SQL：EventDb*/
    List<NormalModel> selectNormalAllEvent(Context context);
    List<TestModel> testAllExample(Context context);
}
