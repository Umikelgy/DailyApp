package com.example.a10068921.myapplication.adapter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a10068921.myapplication.R;
import com.example.a10068921.myapplication.mylayout.AlignedTextView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import java.time.LocalDateTime;
import java.util.List;

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
        public final AlignedTextView alignedTextView;
        public final JCVideoPlayerStandard mVideo;
        public VH(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            alignedTextView=itemView.findViewById(R.id.atv);
            mVideo=itemView.findViewById(R.id.jc_video);
        }
    }
    private List<String> mData;

    public NormalAdapter(List<String> mData) {
        this.mData = mData;
    }

    /**
     *@description
     *在adapter中实现3个方法
     *@anthor  10068921
     */
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {

        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.title.setText(mData.get(position));
        holder.title.setOnClickListener((view)->
                makeText(holder.itemView.getContext(),mData.get(position)+position,Toast.LENGTH_LONG).show()
        );
        holder.time.setText(mData.get(position)+ LocalDateTime.now().toString());
        holder.time.setOnClickListener((view)->
                makeText(holder.itemView.getContext(),LocalDateTime.now().toString(),Toast.LENGTH_LONG).show()
        );
        if(position==55){
            holder.alignedTextView.setText(R.string.text);
        }
        if(position==10){

            holder.mVideo.removeView(holder.itemView);
        }
        holder.itemView.setOnClickListener(view -> {
            // TODO: 2019/1/4 item 点击事件
                makeText(holder.itemView.getContext()," "+position+"item",Toast.LENGTH_LONG).show();
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}
