package com.wmz.demo_w1.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.widget.Toast;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;


public abstract class BaseActivity extends com.wmz.mylibrary.base.BaseActivity {


    @Override
    protected void init() {
        super.init();
        ButterKnife.bind(this);
    }

    private SweetAlertDialog mProgressDialog;
    protected void showProgress(String msg){
        if(mProgressDialog==null) {
            mProgressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        }
        mProgressDialog.setContentText(msg);
        mProgressDialog.show();
    }

    protected void dismissProgress(){
        if(mProgressDialog != null){
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
}