package com.wmz.demo_w1;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.demo_w1.base.BaseFragment;
import com.wmz.mylibrary.adapter.MyFragmentPagerAdapter;
import com.wmz.mylibrary.adapter.ViewHolder;
import com.wmz.mylibrary.listener.OnPageChangeListener;
import com.wmz.mylibrary.tabview.TabView;
import com.wmz.mylibrary.tabview.TabViewChild;
import com.wmz.mylibrary.view.PagerSlidingTab;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TabActivity extends BaseActivity {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_tab;
    }

    @BindView(R.id.fragment_tab)
    PagerSlidingTab mPagerSlidingTab;

    @BindView(R.id.fragment_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.fragment_tabview)
    TabView mTabView;

    @BindView(R.id.fragment_tabview1)
    TabView mTabView1;


    @Override
    protected void initView() {
        initTabView();
        initViewPager();
        initTab();
    }

    private void initTab() {
        ArrayList<String> mList = new ArrayList<>();
        for(int i=0;i<5;i++){
            mList.add(i+1+"");
        }
        mPagerAdapter.setmTitles(mList);
        mPagerSlidingTab.setViewPager(mViewPager);
        mPagerSlidingTab.setTextColor(Color.BLACK);
        mPagerSlidingTab.setTabTextSelectedColor(Color.RED);
        mPagerSlidingTab.setNoline(true);
    }

    private MyFragmentPagerAdapter mPagerAdapter;
    private void initViewPager() {
        ArrayList<MyFragemnt> mList = new ArrayList<>();
        mList.add(new MyFragemnt("1"));
        mList.add(new MyFragemnt("2"));
        mList.add(new MyFragemnt("3"));
        mList.add(new MyFragemnt("4"));
        mList.add(new MyFragemnt("5"));
        mPagerAdapter = new MyFragmentPagerAdapter<MyFragemnt>(getSupportFragmentManager(),mList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTabView.setSelectedPosition(position);
            }
        });
    }

    private void initTabView() {
        List<TabViewChild> mList = new ArrayList<>();
        Fragment fragment = new Fragment();
        mList.add(new com.wmz.mylibrary.tabview.TabViewChild(R.drawable.tab01_sel, R.drawable.tab01_unsel, "1"));
        mList.add(new com.wmz.mylibrary.tabview.TabViewChild(R.drawable.tab02_sel, R.drawable.tab02_unsel, "2"));
        mList.add(new com.wmz.mylibrary.tabview.TabViewChild(R.drawable.tab03_sel, R.drawable.tab03_unsel, "3"));
        mList.add(new com.wmz.mylibrary.tabview.TabViewChild(R.drawable.tab04_sel, R.drawable.tab04_unsel, "4"));
        mList.add(new com.wmz.mylibrary.tabview.TabViewChild(R.drawable.tab05_sel, R.drawable.tab05_unsel, "5"));

        //设置item项的背景颜色
        mTabView.setChildViewSelColor(Color.rgb(0,255,0));
        mTabView.setChildViewUnSelColor(Color.rgb(0,0,255));
        //设置
        mTabView.setTextViewSelectedColor(Color.rgb(255,0,0));
//        mTabView.setTabViewDefaultPosition(1);
        mTabView.setTabViewChild(mList);
        mTabView1.setTabViewChild(mList);

        mTabView.findViewById(R.id.tabview_fragment_container).setVisibility(View.GONE);
        mTabView.setOnTabChildClickListener(new com.wmz.mylibrary.tabview.TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int position, ImageView imageView, TextView textView) {
                mViewPager.setCurrentItem(position);
            }
        });

    }

    static class MyFragemnt extends BaseFragment {
        public String msg = "0";

        public MyFragemnt(String msg) {
            this.msg = msg;
        }

        @Override
        protected void builderData(ViewHolder viewHolder) {
            viewHolder.setText(R.id.btn, msg);
            viewHolder.setOnClickListener(R.id.btn,this);
        }

        @Override
        public int getLayoutResourceId() {
            return R.layout.item_btn;
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(FragmentActivity.this,"onClick-" + msg,Toast.LENGTH_SHORT).initAdapter();
        }
    }
}
