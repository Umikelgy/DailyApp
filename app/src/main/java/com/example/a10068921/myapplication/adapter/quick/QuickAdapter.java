package com.example.a10068921.myapplication.adapter.quick;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/7 9:41
 **/
public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.VH>{
    private List<T> mDatas;
    public QuickAdapter(List<T> datas){
        this.mDatas = datas;
    }

    /**
     * 获取布局Id
     * @param viewType
     * @return
     */
    public abstract int getLayoutId(int viewType);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.get(parent,getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        convert(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 做具体的bind操作
     * @param holder
     * @param data
     * @param position
     */
    public abstract void convert(VH holder, T data, int position);

    static class VH extends RecyclerView.ViewHolder{
        private SparseArray<View> mViews;
        private View mConvertView;

        private VH(View v){
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }

        public static VH get(ViewGroup parent, int layoutId){
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(convertView);
        }

        public <T extends View> T getView(int id){
            View v = mViews.get(id);
            if(v == null){
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T)v;
        }

        public void setText(int id, String value){
            TextView view = getView(id);
            view.setText(value);
        }
    }
}
