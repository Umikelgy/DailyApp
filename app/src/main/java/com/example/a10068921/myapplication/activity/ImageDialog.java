package com.example.a10068921.myapplication.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.example.a10068921.myapplication.R;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/15 10:48
 **/
public class ImageDialog extends Dialog {
  static   ImageDialog imageDialog;
    public ImageDialog(@NonNull Context context) {
        super(context,R.style.edit_AlertDialog_style);
        imageDialog=this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_dialog);
        final PhotoView mView=findViewById(R.id.myPhoto);
        PhotoViewAttacher mAttacher=new PhotoViewAttacher(mView);
        mView.setImageResource(R.mipmap.test_image);
        mAttacher.update();
        mView.setOnClickListener(view -> this.dismiss() );
        setCanceledOnTouchOutside(true);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        dismiss();
        return super.onTouchEvent(event);
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         * */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity= Gravity.BOTTOM;
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height= WindowManager.LayoutParams.MATCH_PARENT;

        getWindow().getDecorView().setPadding(0, 0, 0, 0);

        getWindow().setAttributes(layoutParams);
    }
}
