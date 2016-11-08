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

import com.wmz.mylibrary.utils.WMZLog;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;


public abstract class BaseActivity extends com.wmz.mylibrary.base.BaseActivity {


    @Override
    protected void init() {
        super.init();
        ButterKnife.bind(this);
//        registerDateTransReceiver();
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

    public static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private void registerDateTransReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(CONNECTIVITY_CHANGE_ACTION);
        filter.setPriority(1000);
        registerReceiver(new MyReceiver(), filter);
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals(action, CONNECTIVITY_CHANGE_ACTION)) {//网络变化的时候会发送通知
                showToast("网络变化了");
                return;
            }
        }
    }
}