package com.example.a10068921.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a10068921.myapplication.R;
import com.example.a10068921.myapplication.activity.ImageDialog;
import com.example.a10068921.myapplication.activity.ModifyActivity;
import com.example.a10068921.myapplication.activity.TextDialog;
import com.example.a10068921.myapplication.adapter.quick.QuickAdapter;
import com.example.a10068921.myapplication.sqlite.NormalModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        return R.layout.item_layout;
    }

    @Override
    public void addNewItem(NormalModel normalModel) {
        super.addNewItem(normalModel);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void convert(VH holder, NormalModel data, int position) {
        Context context=holder.itemView.getContext();
       TextView title= holder.getView(R.id.name);
       title.setText(data.getName());

       TextView time=holder.getView(R.id.time);
       time.setText(data.getCreateTime());

       TextView description=holder.getView(R.id.atv);
       description.setText(data.getDescription());
       description.setOnClickListener(view -> new TextDialog(context,data.getDescription()).show());

        ImageView imageView=holder.getView(R.id.item_image);
        if(data.getDescriptionPath()!=null) {
            System.out.println("dataPath= "+data.getDescriptionPath());
            int pathIndex= data.getDescriptionPath().indexOf("|");
            String imgPath = data.getDescriptionPath().substring(0,pathIndex).trim();
            System.out.println("imgPath= "+imgPath);
            String videoPath = data.getDescriptionPath().substring(pathIndex+1).trim();
            System.out.println("videoPath= "+videoPath);
            Stream.of(FileType.IMAGE)
                    .forEach((imageType) ->
                    {
                        if (imgPath != null && imgPath.contains(imageType)) {
                            try {
                                FileInputStream fs = new FileInputStream(imgPath);
                                imageView.setVisibility(View.VISIBLE);
                                imageView.setImageBitmap(BitmapFactory.decodeStream(fs));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            imageView.setOnClickListener((v) -> new ImageDialog(context).show());

            JCVideoPlayerStandard video = holder.getView(R.id.jc_video);
            Stream.of(FileType.VIDEO)
                    .forEach((videoType) ->
                    {
                        if (videoPath != null && videoPath.contains(videoType)) {
                            video.setVisibility(View.VISIBLE);
                            video.setUp(data.getDescriptionPath(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL, data.getName());
                        }
                    });
        }

        holder.itemView.setOnClickListener(v->{
            context.startActivity(new Intent(context, ModifyActivity.class));
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
    }

}
