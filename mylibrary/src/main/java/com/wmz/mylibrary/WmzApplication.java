package com.wmz.mylibrary;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;

import com.wmz.mylibrary.utils.NetUtils;
import com.wmz.mylibrary.utils.ToastUtils;

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
        networkChangeRegisterReceiver();
    }

    private String netWorkType = null;
    private NetworkChangeReceiver networkChangeReceiver;
    public static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private void networkChangeRegisterReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(CONNECTIVITY_CHANGE_ACTION);
        filter.setPriority(1000);
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, filter);
    }

    private void networkChangeUnRegisterReceiver(){
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals(action, CONNECTIVITY_CHANGE_ACTION)) {//网络变化的时候会发送通知
                if(TextUtils.isEmpty(netWorkType)) {
                    netWorkType = NetUtils.getNetworkType(context);
                    ToastUtils.showToast(context, "null-change-"+netWorkType);
                }
                if(NetUtils.hasNetwork(context)) {
                    String currentNetWorkType = NetUtils.getNetworkType(context);
                    if (!netWorkType.equals(currentNetWorkType)) {
                        ToastUtils.showToast(context, netWorkType+"-change-"+currentNetWorkType);
                    }else{
                        netWorkType = currentNetWorkType;
                    }
                }else{
                    ToastUtils.showToast(context, netWorkType+"-change-null");
                    netWorkType = null;
                }
                return;
            }
        }
    }
}
