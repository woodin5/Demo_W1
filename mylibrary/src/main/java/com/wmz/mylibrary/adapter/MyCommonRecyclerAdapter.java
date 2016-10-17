package com.wmz.mylibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wmz on 2016/9/5.
 */
public abstract class MyCommonRecyclerAdapter<T> extends BaseRecyclerAdapter {


    protected MyCommonRecyclerAdapter(Context context, List<T> list) {
        super(context, list);
    }

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
