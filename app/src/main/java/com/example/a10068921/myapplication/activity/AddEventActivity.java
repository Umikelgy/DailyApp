package com.example.a10068921.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a10068921.myapplication.R;
import com.example.a10068921.myapplication.sqlite.NormalModel;
import com.example.a10068921.myapplication.sqlite.sqlthread.AddThread;
import  com.example.a10068921.myapplication.common.FileType;

import java.time.LocalDateTime;


/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/28 11:38
 **/
public class AddEventActivity extends AppCompatActivity {
    private EditText addName;
    private EditText addDescription;
    private TextView uploadImg;
    private TextView uploadVideo;
    private TextView addCancel;
    private TextView addFinish;
    private TextView imgPath;
    private TextView videoPath;

    private static String  path=null;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent_layout);

        addName=findViewById(R.id.add_event_name_edit);
        addDescription=findViewById(R.id.add_event_description_edit);
        uploadImg=findViewById(R.id.add_upload_img);
        uploadVideo=findViewById(R.id.add_upload_video);
        addCancel=findViewById(R.id.add_cancel);
        addFinish=findViewById(R.id.add_finish);
        imgPath=findViewById(R.id.img_path);
        videoPath=findViewById(R.id.video_path);

        uploadImg.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Browser Image..."),1);

        });

        uploadVideo.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Browser video..."),1);

        });

        addCancel.setOnClickListener(view -> finish());

        addFinish.setOnClickListener(view -> {

            String name=addName.getText().toString();
            String description=addDescription.getText().toString();
            String imgPathString;
            String videoPathString;
            if(description.equals(getString(R.string.add_event_description))){
                description=null;
            }
            if(!imgPath.getText().toString().equals(getString(R.string.img_path))){
               imgPathString=imgPath.getText().toString();
            }else {
                imgPathString="";
            }
            if(!videoPath.getText().toString().equals(getString(R.string.video_path))){
                videoPathString=videoPath.getText().toString();
            }else {
                videoPathString="";
            }
            if(isString(name)&&isString(description)) {
                new Thread(new AddThread(AddEventActivity.this, NormalModel.newBuilder()
                        .name(name)
                        .createTime(LocalDateTime.now().toString())
                        .description(description)
                        .updateTime(LocalDateTime.now().toString())
                        .descriptionPath(imgPathString + "|" + videoPathString)
                        .builder())).start();
                finish();
            }else {
                Toast.makeText(AddEventActivity.this,"名字或内容不能为空",Toast.LENGTH_LONG).show();
            }
        });
    }
    private boolean isString(String s){
        if(s!=null&&!s.trim().equals("")){
            return true;
        }else {
            return false;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            Uri uri = data.getData();
            path = uri.getPath();
         switch (pathType(path)){
             case 1:imgPath.setText(path);break;
             case 2:videoPath.setText(path);break;
             default:Toast.makeText(AddEventActivity.this,"选择路径有误，请重新选择",Toast.LENGTH_SHORT).show();
             System.out.println("错误地址："+path);break;
         }
        }

    }
    private int pathType(String path){
        if(path==null){
            return 0;
        }
        int index=path.trim().indexOf(".");
        String pass=path.substring(index);

        if(index==-1){
            return 0;
        }
        for(String type:FileType.IMAGE){
            if(type.equals(pass)){
//                返回1 ：表示图片格式
                return 1;
            }
        }
        for(String type:FileType.VIDEO){
            if(type.equals(pass)){
//                返回2 ：表示视频格式
                return 2;
            }
        }

       return 0;
    }
}
