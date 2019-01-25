package com.example.a10068921.myapplication.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a10068921.myapplication.R;
import com.example.a10068921.myapplication.activity.ImageDialog;
import com.example.a10068921.myapplication.activity.TextDialog;
import com.example.a10068921.myapplication.sqlite.NormalModel;
import com.example.a10068921.myapplication.sqlite.SqliteUtils;
import com.example.a10068921.myapplication.sqlite.TestModel;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static android.widget.Toast.makeText;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/4 19:28
 **/
public class NormalAdapter extends RecyclerView.Adapter <NormalAdapter.VH> {

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView title;
        public final TextView time;
        public final ImageView imageView;
        public final TextView textView;
        public final JCVideoPlayerStandard mVideo;
        public VH(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            textView=itemView.findViewById(R.id.atv);
            mVideo=itemView.findViewById(R.id.jc_video);
            imageView=itemView.findViewById(R.id.item_image);
        }
    }
    private List<NormalModel> mData;

    public NormalAdapter(List<NormalModel> mData) {
        this.mData = mData;
    }
    /**
     *@description
     *在adapter中实现3个方法
     *@anthor  10068921
     */
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(VH holder, int position) {
        NormalModel normalModel=mData.get(position);
        Context context=holder.itemView.getContext();
        holder.title.setText(normalModel.getName());
        holder.title.setOnClickListener((view)->
                makeText(context,normalModel.getName()+position,Toast.LENGTH_LONG).show()
        );
        holder.time.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(normalModel.getCreateTime()));

        TextView textView=  holder.textView;
       textView .setText(normalModel.getDescription());
        textView.setOnClickListener(view -> new TextDialog(context,normalModel.getDescription()).show());
        // TODO: 2019/1/25 图片，视频
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
