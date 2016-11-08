package com.wmz.mylibrary.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by liuwei on 2016/9/6.
 */
public class ToastUtils {

    private static String oldMsg;
    protected static Toast toast   = null;
    private static long oldTime=0; //上一次点击时间
    private static long newTime=0; //当前点击时间

    /**
     * @param mContext ： 上下文信息
     * @param contentText : toast所要显示文本内容
     */
    public static void showToast(Context mContext, String contentText) {
        showWithShieldRepeats(mContext,contentText);
    }

    /**
     *  屏蔽多次点击重复显示
     */
    private static void showWithShieldRepeats(Context mContext, String contentText){
        if(TextUtils.isEmpty(contentText)||(mContext==null)){
            return ;
        }

        if(toast == null){
            toast=Toast.makeText(mContext,contentText,Toast.LENGTH_SHORT);
            toast.show();
            oldTime=System.currentTimeMillis();
        }else {
            newTime = System.currentTimeMillis();
            if(contentText.equals(oldMsg)){
                if((newTime-oldTime)>Toast.LENGTH_SHORT){
                    toast.show();
                }
            }else {
                oldMsg = contentText;
                toast.setText(contentText);
                toast.show();
            }
        }
        oldTime=newTime;
    }

}