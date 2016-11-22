package com.wmz.mylibrary.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

/**
 * Created by wmz on 2016/10/26.
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
     * 从网络中加载图片，默认图片占位
     * @param context
     * @param defaultRes
     * @param url
     * @param imageView
     */
    public static void into(Context context,int defaultRes, String url, ImageView imageView){
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaultRes).into(imageView);
    }

    /**
     * 从网络中加载图片，默认图片占位,返回错误图片提示
     * @param context
     * @param defaultRes
     * @param errorRes
     * @param url
     * @param imageView
     */
    public static void into(Context context,int defaultRes,int errorRes, String url, ImageView imageView){
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaultRes).error(errorRes).into(imageView);
    }

    public static void show(Context context,int defaultRes,int errorRes, String url, ImageView imageView){
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaultRes).error(errorRes).crossFade().into(imageView);
    }

    public static void show(Context context,int defaultRes,int errorRes,int duration, String url, ImageView imageView){
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaultRes).error(errorRes).crossFade(duration).into(imageView);
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

    /**
     * 设置前景
     */
    public static <T> void load(Context mContext, T t, ImageView imageView, int errorId) {
        boolean isErrorImage = true;
        if (errorId <= 0) {
            isErrorImage = false;
        }

        RequestManager picasso = Glide.with(mContext);
        DrawableTypeRequest creator = null;
        if (t != null) {
            if (t instanceof Uri) {//图片本地路径
                creator = picasso.load((Uri) t);
            } else if (t instanceof String) {//图片网络路径
                creator = picasso.load((String) t);
            } else if (t instanceof File) {//文件
                creator = picasso.load((File) t);
            } else if (t instanceof Integer) {//资源Id
                creator = picasso.load((Integer) t);
            }
        }

        DrawableRequestBuilder builder = null;
        if (isErrorImage){
            builder = creator.error(errorId);
            builder.into(imageView);
            return;
        }
        creator.into(imageView);
    }

    public static <T> void load(Context mContext, T t, ImageView imageView) {
        load(mContext, t, imageView, 0);
    }


}
