package com.wmz.demo_w1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.mylibrary.view.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FlyBannerActivity extends BaseActivity {


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_fly_banner;
    }

    @BindView(R.id.banner_1)
    FlyBanner mBannerLocal;//加载本地图片

    @BindView(R.id.banner_2)
     FlyBanner mBannerNet;//加载网络图片

    private String[] mImagesUrl = {
            "http://img4.imgtn.bdimg.com/it/u=2430963138,1300578556&fm=23&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2755648979,3568014048&fm=23&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2272739960,4287902102&fm=23&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=1078051055,1310741362&fm=23&gp=0.jpg"
    };

    @Override
    protected void initData() {
        initLocalBanner();
        initNetBanner();
    }

    /**
     * 加载本地图片
     */
    private void initLocalBanner() {

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.ic_google_messenger_icon);
        images.add(R.drawable.ic_google_maps_icon);
        mBannerLocal.setImages(images);
//        mBannerLocal.setPoinstPosition(FlyBanner.RIGHT);

        mBannerLocal.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                toast("点击了第"+position+"张图片");
            }
        });
    }

    /**
     * 加载网页图片
     */
    private void initNetBanner() {

        List<String> imgesUrl = new ArrayList<>();
        for (int i = 0; i < mImagesUrl.length; i++) {
            imgesUrl.add(mImagesUrl[i]);
        }
        mBannerNet.setImagesUrl(imgesUrl);

        mBannerNet.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                toast("点击了第" + position + "张图片");
            }
        });
    }

    private void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}
