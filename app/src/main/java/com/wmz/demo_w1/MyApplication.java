package com.wmz.demo_w1;

import android.app.Application;

import com.wmz.mylibrary.crash.CustomCrash;

/**
 * Created by Administrator on 2016/10/12.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化崩溃日志收集器
        CustomCrash mCustomCrash= CustomCrash.getInstance();
    }
}
