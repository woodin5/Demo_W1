package com.wmz.demo_w1.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;

import com.android.network.monitor.NetworkManager;
import com.android.network.monitor.NetworkObserver;
import com.android.network.monitor.NetworkType;
import com.wmz.mylibrary.utils.WMZLog;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;


public abstract class BaseActivity extends com.wmz.mylibrary.base.BaseActivity {


    @Override
    protected void init() {
        super.init();
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        if (networkObserver != null) {
            NetworkManager.getInstance().initialized(this);
            NetworkManager.getInstance().register(networkObserver);
        }
    }

    private SweetAlertDialog mProgressDialog;

    protected void showProgress(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        }
        mProgressDialog.setContentText(msg);
        mProgressDialog.show();
    }

    protected void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    private Toast mToast;

    protected void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    //网络监听观察者
    private NetworkObserver networkObserver =  new NetworkObserver() {
        @Override
        public void onNetworkStateChanged(NetAction action) {
            onNetworkChanged(action.getType());
        }
    };

    public void onNetworkChanged(NetworkType netWorkType) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (networkObserver != null) {
            NetworkManager.getInstance().unregister(networkObserver);
        }
    }
}