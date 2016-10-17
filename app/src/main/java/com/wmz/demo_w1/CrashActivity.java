package com.wmz.demo_w1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.wmz.demo_w1.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CrashActivity extends BaseActivity {

    @OnClick(R.id.crash_btn)
    void crash(){
    }
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_crash;
    }
}
