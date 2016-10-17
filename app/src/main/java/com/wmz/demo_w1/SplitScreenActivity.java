package com.wmz.demo_w1;

import com.wmz.demo_w1.base.BaseActivity;

import butterknife.OnClick;

public class SplitScreenActivity extends BaseActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_split_screen;
    }

    @OnClick(R.id.splitscreen_show)
    void show(){
        DisplayManagerTools.show(SplitScreenActivity.this);
    }
}
