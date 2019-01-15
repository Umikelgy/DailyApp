package com.example.a10068921.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.a10068921.myapplication.R;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/15 10:25
 **/
public class ImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_dialog);

        final PhotoView mView=findViewById(R.id.myPhoto);
        PhotoViewAttacher mAttacher=new PhotoViewAttacher(mView);
        mView.setImageResource(R.mipmap.test_image);

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAttacher.update();
    }
}
