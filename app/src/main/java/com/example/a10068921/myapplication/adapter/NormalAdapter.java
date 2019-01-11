package com.example.a10068921.myapplication.adapter;

import android.app.Dialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a10068921.myapplication.R;
import com.example.a10068921.myapplication.mylayout.AlignedTextView;
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
        public final AlignedTextView alignedTextView;
        public final JCVideoPlayerStandard mVideo;
        public VH(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            alignedTextView=itemView.findViewById(R.id.atv);
            mVideo=itemView.findViewById(R.id.jc_video);
            imageView=itemView.findViewById(R.id.item_image);
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
        holder.title.setText(mData.get(position))                                   ;
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
            String sql="select * from example1";
            SqliteUtils sqliteUtils=new SqliteUtils(holder.itemView.getContext(),sql);
            Map<String ,Object> nameMap=sqliteUtils.selectTableMassage(null);
            sqliteUtils.close();
            holder.title.setText(nameMap.toString());
            holder.mVideo.setVisibility(View.GONE);

        }
        if(position==5) {
            holder.imageView.setImageResource(R.mipmap.test_image);
            holder.imageView.setOnClickListener((v)->
            {
              openBigImage(v).show();
            });
        }
        holder.itemView.setOnClickListener(view -> {
            // TODO: 2019/1/4 item 点击事件
                makeText(holder.itemView.getContext()," "+position+"item",Toast.LENGTH_LONG).show();
        });
    }

    private Dialog openBigImage(View view) {
        Dialog imageDialog=new Dialog(view.getContext(),R.style.edit_AlertDialog_style);
        imageDialog.setContentView(R.layout.show_big_image);
        /**
         * 点击其他区域消失
         * */
        imageDialog.setCanceledOnTouchOutside(true);
        Window w = imageDialog.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 40;
        imageDialog.onWindowAttributesChanged(lp);
        ImageView iv_big_image=imageDialog.findViewById(R.id.iv_big_image);
        iv_big_image.setBackgroundResource(R.mipmap.test_image);
        iv_big_image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv_big_image.setOnClickListener((v->
        {
            if(imageDialog!=null&& imageDialog.isShowing()){
                imageDialog.dismiss();
            }
        }));
return imageDialog;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

}
