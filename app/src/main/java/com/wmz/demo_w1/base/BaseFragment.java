package com.wmz.demo_w1.base;

import android.view.View;

import com.wmz.mylibrary.adapter.ViewHolder;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by wmz on 2016/9/23.
 */

public abstract class BaseFragment extends com.wmz.mylibrary.base.BaseFragment {

    @Override
    public void initView(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected void builderData(ViewHolder viewHolder) {

    }

    private SweetAlertDialog mProgressDialog;
    protected void showProgress(String msg){
        if(mProgressDialog==null) {
            mProgressDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        }
        mProgressDialog.setTitleText(msg);


        mProgressDialog.show();
    }

    protected void dismissProgress(){
        if(mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }
}
