package com.wmz.demo_w1;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;


public abstract class BaseActivity extends com.wmz.mylibrary.base.BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @Override
    protected void initView() {

    }
}