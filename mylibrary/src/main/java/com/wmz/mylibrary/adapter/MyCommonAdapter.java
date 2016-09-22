package com.wmz.mylibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wmz on 2016/9/5.
 */
public abstract class MyCommonAdapter<T> extends MyBaseAdapter {


    protected MyCommonAdapter(Context context, List<T> list) {
        super(context, list);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = ViewHolder.get(convertView, parent, builderView(mInflater));
        // 绑定数据
        builderData(viewHolder, list.get(position), position);

        return viewHolder.getItemView();

    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Object viewHolder = null;
//        if (convertView == null) {
//            convertView = builderView(mInflater);
//            viewHolder = builderHolder(convertView);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = convertView.getTag();
//        }
//        builderData(viewHolder, list.get(position));
//
//        return convertView;
//    }

    /**
     * 填充数据
     *
     * @param viewHolder
     * @param obj
     */
    // protected abstract void builderData(Object viewHolder, Object obj);
    protected abstract void builderData(ViewHolder viewHolder, Object obj, int position);

    /**
     * 构造viewholder
     *
     * @param convertView
     * @return
     */
    //protected abstract Object builderHolder(View convertView);

    /**
     * 初始化view
     *
     * @param inflater
     * @return
     */
//    protected abstract View builderView(LayoutInflater inflater);
    protected abstract int builderView(LayoutInflater inflater);

    /**
     * 进行adapter更新
     *
     * @param isRefresh
     * @param list
     */
    protected void refreshData(boolean isRefresh, List<T> list) {
        if (isRefresh) {
            list.addAll(list);
        } else {
            list.clear();
        }
    }


}
