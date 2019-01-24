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
import com.example.a10068921.myapplication.sqlite.SqliteUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import java.time.LocalDateTime;
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
    private List<String> mData;

    public NormalAdapter(List<String> mData) {
        this.mData = mData;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull VH holder) {
        mData.add("item"+10);
        notifyItemInserted(mData.size()-1);
        super.onViewAttachedToWindow(holder);
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
        Context context=holder.itemView.getContext();
        holder.title.setText(mData.get(position));
        holder.title.setOnClickListener((view)->
                makeText(context,mData.get(position)+position,Toast.LENGTH_LONG).show()
        );
        holder.time.setText(mData.get(position)+ LocalDateTime.now().toString());
        holder.time.setOnClickListener((view)->
                makeText(context,LocalDateTime.now().toString(),Toast.LENGTH_LONG).show()
        );
        if(position==10){
            String sql="select * from example1";
            SqliteUtils sqliteUtils=new SqliteUtils(context,sql);
            Map<String ,Object> nameMap=sqliteUtils.selectTableMassage(null);
            sqliteUtils.close();
            holder.title.setText(nameMap.toString());
        }
        if(position==4){
            holder.imageView.setVisibility(View.GONE);
            holder.textView.setVisibility(View.GONE);
            holder.mVideo.setVisibility(View.GONE);
        }
        if(position==5) {
            TextView textView= holder.textView;
            textView.setText(R.string.text);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  new TextDialog(context,context.getString(R.string.text)).show();
                }
            });

         ImageView image=holder.imageView;
         image.setImageResource(R.mipmap.test_image);
         image.setOnClickListener((v)->
         {
             ImageDialog imageDialog=new ImageDialog(context);
             imageDialog.show();
         });
        }
        holder.itemView.setOnClickListener(view -> {
            // TODO: 2019/1/4 item 点击事件
                makeText(context," "+position+"item",Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
