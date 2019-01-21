package com.example.a10068921.myapplication.common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/21 15:45
 **/
public class Permission {
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "Manifest.permission.WRITE_EXTERNAL_STORAGE"};


    public static void  permission(Context context, Activity activity){
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(context, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,1);

        } else {
//            Toast.makeText(context, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e("tag", "checkPermission: 已经授权！");
        }
    }

}
