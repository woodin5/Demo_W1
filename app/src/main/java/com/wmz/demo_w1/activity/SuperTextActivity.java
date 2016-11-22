package com.wmz.demo_w1.activity;

import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.mylibrary.utils.ToastUtils;
import com.wmz.mylibrary.view.SuperTextView;

import butterknife.BindView;

public class SuperTextActivity extends BaseActivity {

    @BindView(R.id.stv)
    SuperTextView mStv;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_super_text;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mStv.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener(){
            @Override
            public void onSuperTextViewClick() {
                super.onSuperTextViewClick();
                ToastUtils.showToast(mContext,"onSuperTextViewClick");
            }
        });
    }
}
