package com.wmz.mylibrary;

import android.app.Application;

/**
 * Created by wmz on 2016/11/8.
 */

public class WmzApplication extends Application {
    private static WmzApplication instance;
    public WmzApplication() {
    }

    // 单例模式获取唯一的Application实例
    public static WmzApplication getInstance() {
        if (null == instance) {
            instance = new WmzApplication();
        }
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

}
