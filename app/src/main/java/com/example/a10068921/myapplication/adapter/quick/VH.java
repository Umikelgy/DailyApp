package com.example.a10068921.myapplication.adapter.quick;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/7 9:20
 **/
public class VH extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    public VH(View itemView) {
        super(itemView);
        mConvertView=itemView;
        mViews=new SparseArray<>();
    }
    public static VH get(ViewGroup parent,int layoutId){
        View convertView= LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
        return new VH(convertView);
    }
    public <T extends View> T getView(int id){

        View v=mViews.get(id);
        if (v == null) {
            v=mConvertView.findViewById(id);
            mViews.put(id,v);
        }
        return (T)v;
    }
    public void setText(int id ,String value){
        TextView view=getView(id);
        view.setText(value);
    }
}
