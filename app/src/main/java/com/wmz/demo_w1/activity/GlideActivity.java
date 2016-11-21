package com.wmz.demo_w1.activity;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.mylibrary.adapter.MyCommonAdapter;
import com.wmz.mylibrary.adapter.ViewHolder;
import com.wmz.mylibrary.utils.GlideUtils;
import com.wmz.mylibrary.view.HorizontalListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class GlideActivity extends BaseActivity {
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";
    private int resId = R.drawable.android;
    private String imgUrl = "http://i.imgur.com/DvpvklR.png";
    private Uri uri;
    @BindView(R.id.hlv)
    HorizontalListView mHlv;
    @BindView(R.id.iv)
    ImageView mIv;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_glide;
    }

    @Override
    protected void initData() {
        super.initData();
        String[] arrs = getResources().getStringArray(R.array.glide_operate);
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(arrs));
        mHlv.setAdapter(new MyAdapter(mContext,list));

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mHlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showIv(position);
            }
        });
    }

    private void showIv(int position) {
        switch (position){
            case 0:
                GlideUtils.into(mContext,resId,mIv);
                break;
            case 1:
                break;
            case 2:
                uri = Uri.parse(ANDROID_RESOURCE + mContext.getPackageName() + FOREWARD_SLASH + R.drawable.android);
                GlideUtils.into(mContext,uri,mIv);
                break;
            case 3:
                GlideUtils.into(mContext,imgUrl,mIv);
                break;
            case 4:
                GlideUtils.into(mContext,R.drawable.android,imgUrl,mIv);
                break;
        }
    }

    class MyAdapter extends MyCommonAdapter<String>{

        protected MyAdapter(Context context, List<String> list) {
            super(context, list);
        }

        @Override
        protected void builderData(ViewHolder viewHolder, Object obj, int position) {
            if(obj==null) return;
            viewHolder.setText(R.id.btn,obj.toString());
        }

        @Override
        protected int builderView(LayoutInflater inflater) {
            return R.layout.item_btn;
        }
    }
}
