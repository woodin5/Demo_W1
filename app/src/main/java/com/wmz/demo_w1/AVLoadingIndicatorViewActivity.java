package com.wmz.demo_w1;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.mylibrary.adapter.MyCommonRecyclerAdapter;
import com.wmz.mylibrary.adapter.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

public class AVLoadingIndicatorViewActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecycler;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_avloading_indicator_view;
    }

    @Override
    protected void initData() {
        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
        mRecycler.setLayoutManager(layoutManager);

        ArrayList<String> mList = new ArrayList<>();
        mList.addAll(Arrays.asList(INDICATORS));
        mRecycler.setAdapter(new MyCommonRecyclerAdapter(AVLoadingIndicatorViewActivity.this,mList) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_indicator;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, Object item) {

                ((AVLoadingIndicatorView)holder.findViewById(R.id.indicator)).setIndicator(item.toString());
            }

        });
    }

    private static final String[] INDICATORS = new String[]{
            "BallPulseIndicator",
            "BallGridPulseIndicator",
            "BallClipRotateIndicator",
            "BallClipRotatePulseIndicator",
            "SquareSpinIndicator",
            "BallClipRotateMultipleIndicator",
            "BallPulseRiseIndicator",
            "BallRotateIndicator",
            "CubeTransitionIndicator",
            "BallZigZagIndicator",
            "BallZigZagDeflectIndicator",
            "BallTrianglePathIndicator",
            "BallScaleIndicator",
            "LineScaleIndicator",
            "LineScalePartyIndicator",
            "BallScaleMultipleIndicator",
            "BallPulseSyncIndicator",
            "BallBeatIndicator",
            "LineScalePulseOutIndicator",
            "LineScalePulseOutRapidIndicator",
            "BallScaleRippleIndicator",
            "BallScaleRippleMultipleIndicator",
            "BallSpinFadeLoaderIndicator",
            "LineSpinFadeLoaderIndicator",
            "TriangleSkewSpinIndicator",
            "PacmanIndicator",
            "BallGridBeatIndicator",
            "SemiCircleSpinIndicator",
            "com.wang.avi.sample.MyCustomIndicator"
    };



}
