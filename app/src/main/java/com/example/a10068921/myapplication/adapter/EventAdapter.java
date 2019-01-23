package com.example.a10068921.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.a10068921.myapplication.R;
import com.example.a10068921.myapplication.activity.ImageDialog;
import com.example.a10068921.myapplication.activity.TextDialog;
import com.example.a10068921.myapplication.adapter.quick.QuickAdapter;
import com.example.a10068921.myapplication.sqlite.NormalModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import  com.example.a10068921.myapplication.common.FileType;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/21 15:44
 **/
public class EventAdapter extends QuickAdapter<NormalModel> {
    private List<NormalModel> data;
    public EventAdapter(List<NormalModel> data) {
        super(data);
        this.data=data;
    }

    @Override
    public int getLayoutId(int viewType) {
        return data.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void convert(VH holder, NormalModel data, int position) {
        Context context=holder.itemView.getContext();
       TextView title= holder.getView(R.id.name);
       title.setText(data.getName());

       TextView time=holder.getView(R.id.time);
        DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       time.setText(df.format(data.getCreateTime()));

       TextView description=holder.getView(R.id.atv);
       description.setText(data.getDescription());
       description.setOnClickListener(view -> new TextDialog(context,data.getDescription()).show());

        ImageView imageView=holder.getView(R.id.item_image);
        String path=data.getDescriptionPath();
        Stream.of(FileType.IMAGE)
                .forEach((imageType)->
                {
                    if (path.contains(imageType)) {
                        try {
                            FileInputStream fs=new FileInputStream(path);
                            imageView.setVisibility(View.VISIBLE);
                            imageView.setImageBitmap(BitmapFactory.decodeStream(fs));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
        imageView.setOnClickListener((v)->new ImageDialog(context).show());

        JCVideoPlayerStandard video=holder.getView(R.id.jc_video);
        Stream.of(FileType.VIDEO)
                .forEach((videoType)->
                {
                    if(path.contains(videoType)){
                        video.setVisibility(View.VISIBLE);
                        video.setUp(data.getDescriptionPath(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL,data.getName());
                    }
                });


    }
}
