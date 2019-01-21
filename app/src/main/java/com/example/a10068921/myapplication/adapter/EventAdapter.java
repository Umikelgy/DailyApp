package com.example.a10068921.myapplication.adapter;

import com.example.a10068921.myapplication.adapter.quick.QuickAdapter;
import com.example.a10068921.myapplication.sqlite.NormalModel;

import java.util.List;

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

    @Override
    public void convert(VH holder, NormalModel data, int position) {

    }
}
