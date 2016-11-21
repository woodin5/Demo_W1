package com.wmz.mylibrary.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Administrator on 2016/10/26.
 */

public class GlideUtils {

    /**
     * 从资源id中加载图片
     * @param context
     * @param resId
     * @param imageView
     */
    public static void into(Context context, int resId, ImageView imageView){
        Glide.with(context).load(resId).into(imageView);
    }

    /**
     * 从网络中加载图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void into(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }

    /**
     * 从Uri中加载图片
     * @param context
     * @param uri
     * @param imageView
     */
    public static void into(Context context, Uri uri, ImageView imageView){
        Glide.with(context).load(uri).into(imageView);
    }

    /**
     * 从网络中加载图片，
     * @param context
     * @param defaultRes
     * @param url
     * @param imageView
     */
    public static void into(Context context,int defaultRes, String url, ImageView imageView){
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaultRes).into(imageView);
    }

    /**
     * 加载圆形图片
     * @param context
     * @param defaultRes
     * @param url
     * @param imageView
     */
    public static void CircleTransform(Context context,int defaultRes, String url, ImageView imageView){
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaultRes).transform(new CircleTransform(context)).into(imageView);
    }

    /**
     * 加载圆角图片
     * @param context
     * @param defaultRes
     * @param url
     * @param imageView
     */
    public static void RoundTransform(Context context,int defaultRes, String url, ImageView imageView){
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaultRes).transform(new RoundTransform(context)).into(imageView);
    }
}
