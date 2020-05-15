package com.example.datebindingdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import java.util.List;

public class CommonAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> list;
    private int layout_id;
    private int variableId;

    public CommonAdapter(Context context, List<T> list, int layout_id, int variableId) {
        this.context = context;
        this.list = list;
        this.layout_id = layout_id;
        this.variableId = variableId;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewDataBinding binding = null;
        if (view == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), layout_id, viewGroup, false);
        }else{
            binding = DataBindingUtil.getBinding(view);
        }
        binding.setVariable(variableId,list.get(i));
        return binding.getRoot();
    }
}
