package com.wmz.mylibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.wmz.mylibrary.EventBusMessageEvent;
import com.wmz.mylibrary.manager.AppManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(getLayoutResourceId());
        EventBus.getDefault().register(this);
        init();
        initView();
        //管理activity
        AppManager.getAppManager().addActivity(this);
    }

    /**
     * 设置布局文件
     *
     * @return
     */
    protected abstract int getLayoutResourceId();


    /**
     * 初始化View之前做的事
     */
    protected void init() {

    }

    /**
     * 初始化空控件
     */
    protected abstract void initView();

    /**
     * 分装findviewById
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T IfindViewById(int id) {
        return (T) super.findViewById(id);
    }

    /**
     * 启动不带参数的Aactvity
     *
     * @param clazz
     */
    protected void launchActvity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 启动带参数的Activity
     *
     * @param clazz
     * @param extras
     */
    protected void launchActvityWithBundle(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
        EventBus.getDefault().unregister(this);
    }

    /**
     * 完全退出应用
     */
    protected void closeApp() {
        AppManager.getAppManager().AppExit(mContext);
    }

    @Subscribe
    public void handleEventBus(EventBusMessageEvent event){
    }

    @Override
    public void onClick(View v) {

    }
}