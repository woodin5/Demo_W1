package com.wmz.mylibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmz on 2016/9/5.
 */
public abstract class MyBaseAdapter<T> extends android.widget.BaseAdapter{
    protected List<T> list = new ArrayList<>();
    protected Context context;
    protected LayoutInflater mInflater;

    protected MyBaseAdapter(Context context, List<T> list) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return list != null ? list.get(position) : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    }