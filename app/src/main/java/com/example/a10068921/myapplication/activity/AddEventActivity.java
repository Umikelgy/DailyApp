package com.example.a10068921.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import com.example.a10068921.myapplication.R;

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



    }
}
