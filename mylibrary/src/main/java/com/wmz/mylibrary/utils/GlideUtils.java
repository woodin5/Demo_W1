package com.wmz.mylibrary.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Administrator on 2016/10/26.
 */

public class GlideUtils {

    public static void into(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }

    public static void into(Context context,int defaultRes, String url, ImageView imageView){
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaultRes).into(imageView);
    }

    public static void CircleTransform(Context context,int defaultRes, String url, ImageView imageView){
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaultRes).transform(new CircleTransform(context)).into(imageView);
    }

    public static void RoundTransform(Context context,int defaultRes, String url, ImageView imageView){
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaultRes).transform(new RoundTransform(context)).into(imageView);
    }
}
