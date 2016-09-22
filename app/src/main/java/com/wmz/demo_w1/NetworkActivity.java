package com.wmz.demo_w1;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NetworkActivity extends AppCompatActivity {
    @BindView(R.id.network_viewpager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager() {

    }
}
