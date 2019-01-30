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
import com.example.a10068921.myapplication.R;
import com.example.a10068921.myapplication.sqlite.NormalModel;
import com.example.a10068921.myapplication.sqlite.sqlthread.AddThread;

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

            imgPath.setText(path);
        });

        uploadVideo.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Browser video..."),1);

            videoPath.setText(path);
        });

        addCancel.setOnClickListener(view -> finish());

        addFinish.setOnClickListener(view -> {

            new Thread(new AddThread(AddEventActivity.this, NormalModel.newBuilder()
                    .name(addName.getText().toString())
                    .createTime(LocalDateTime.now().toString())
                    .description(addDescription.getText().toString())
                    .updateTime(LocalDateTime.now().toString())
                    .descriptionPath(imgPath.getText().toString()+"|"+videoPath.getText().toString())
                    .builder())).start();
            finish();
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            Uri uri=data.getData();
            path= uri.getPath();
        }

    }
}
