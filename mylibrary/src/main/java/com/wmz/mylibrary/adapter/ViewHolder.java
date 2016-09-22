package com.wmz.mylibrary.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

/**
 * Created by wmz on 2016/9/5.
 */
public class ViewHolder {
    /**
     * ViewHolder实现类,桥接模式适配AbsListView与RecyclerView的二维变化
     */
    ViewHolderImpl mHolderImpl;


    /**
     * @param itemView
     */
    ViewHolder(View itemView) {
        mHolderImpl = new ViewHolderImpl(itemView);
    }

    /**
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T findViewById(int viewId) {
        return mHolderImpl.findViewById(viewId);
    }

    public Context getContext() {
        return mHolderImpl.mItemView.getContext();
    }

    /**
     * 获取GodViewHolder
     *
     * @param convertView
     * @param parent
     * @param layoutId
     * @return
     */
    public static ViewHolder get(View convertView, ViewGroup parent, int layoutId) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return viewHolder;
    }


    public View getItemView() {
        return mHolderImpl.getItemView();
    }

    public ViewHolder setText(int viewId, int stringId) {
        mHolderImpl.setText(viewId, stringId);
        return this;
    }

    public ViewHolder setText(int viewId, String text) {
        mHolderImpl.setText(viewId, text);
        return this;
    }

    public ViewHolder setTextColor(int viewId, int color) {
        mHolderImpl.setTextColor(viewId, color);
        return this;
    }

    /**
     * @param viewId
     * @param color
     */
    public ViewHolder setBackgroundColor(int viewId, int color) {
        mHolderImpl.setBackgroundColor(viewId, color);
        return this;
    }


    /**
     * @param viewId
     * @param resId
     */
    public ViewHolder setBackgroundResource(int viewId, int resId) {
        mHolderImpl.setBackgroundResource(viewId, resId);
        return this;
    }


    /**
     * @param viewId
     * @param drawable
     */
    public ViewHolder setBackgroundDrawable(int viewId, Drawable drawable) {
        mHolderImpl.setBackgroundDrawable(viewId, drawable);
        return this;
    }

    /**
     * @param viewId
     * @param drawable
     */
    @TargetApi(16)
    public ViewHolder setBackground(int viewId, Drawable drawable) {
        mHolderImpl.setBackground(viewId, drawable);
        return this;
    }


    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        mHolderImpl.setImageBitmap(viewId, bitmap);
        return this;
    }


    public ViewHolder setImageResource(int viewId, int resId) {
        mHolderImpl.setImageResource(viewId, resId);
        return this;
    }

    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        mHolderImpl.setImageDrawable(viewId, drawable);
        return this;
    }


    public ViewHolder setImageDrawable(int viewId, Uri uri) {
        mHolderImpl.setImageDrawable(viewId, uri);
        return this;
    }


    @TargetApi(16)
    public ViewHolder setImageAlpha(int viewId, int alpha) {
        mHolderImpl.setImageAlpha(viewId, alpha);
        return this;
    }

    public ViewHolder setChecked(int viewId, boolean checked) {
        mHolderImpl.setChecked(viewId, checked);
        return this;
    }


    public ViewHolder setProgress(int viewId, int progress) {
        mHolderImpl.setProgress(viewId, progress);
        return this;
    }

    public ViewHolder setProgress(int viewId, int progress, int max) {
        mHolderImpl.setProgress(viewId, progress, max);
        return this;
    }

    public ViewHolder setMax(int viewId, int max) {
        mHolderImpl.setMax(viewId, max);
        return this;
    }

    public ViewHolder setRating(int viewId, float rating) {
        mHolderImpl.setRating(viewId, rating);
        return this;
    }


    public ViewHolder setRating(int viewId, float rating, int max) {
        mHolderImpl.setRating(viewId, rating, max);
        return this;
    }

    public ViewHolder setVisibility(int viewId, int visible) {
        mHolderImpl.setVisibility(viewId, visible);
        return this;
    }

    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        mHolderImpl.setOnClickListener(viewId, listener);
        return this;
    }

    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        mHolderImpl.setOnTouchListener(viewId, listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        mHolderImpl.setOnLongClickListener(viewId, listener);
        return this;
    }

    public ViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        mHolderImpl.setOnItemClickListener(viewId, listener);
        return this;
    }


    public ViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        mHolderImpl.setOnItemLongClickListener(viewId, listener);
        return this;
    }


    public ViewHolder setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        mHolderImpl.setOnItemSelectedClickListener(viewId, listener);
        return this;
    }
}
