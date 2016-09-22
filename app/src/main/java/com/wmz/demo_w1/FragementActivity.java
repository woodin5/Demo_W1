package com.wmz.demo_w1;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wmz.mylibrary.adapter.MyFragmentPagerAdapter;
import com.wmz.mylibrary.base.BaseFragment;
import com.wmz.mylibrary.view.PagerSlidingTab;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FragementActivity extends AppCompatActivity {

    @BindView(R.id.fragment_tab)
    PagerSlidingTab mTab;

    @BindView(R.id.fragment_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.fragment_tabview)
    TabView mTabView;

    @BindView(R.id.fragment_tabview_1)
    com.wmz.mylibrary.tabview.TabView mTabView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragement);

        mTabView = (TabView) findViewById(R.id.fragment_tabview);
        mTabView1 = (com.wmz.mylibrary.tabview.TabView) findViewById(R.id.fragment_tabview_1);
        mViewPager = (ViewPager) findViewById(R.id.fragment_viewpager);

        initTabView();
        initTabView1();
        initViewPager();
        initTab();
    }

    private void initTab() {
        ArrayList<String> mList = new ArrayList<>();
        for(int i=0;i<5;i++){
            mList.add(i+1+"");
        }
//        mPagerAdapter.setmTitles(mList);
//        mTab.setViewPager(mViewPager);
    }

    private MyFragmentPagerAdapter mPagerAdapter;
    private void initViewPager() {
        ArrayList<OneFragemnt> mList = new ArrayList<>();
        OneFragemnt fragment1 = new OneFragemnt("1");
        OneFragemnt fragment2 = new OneFragemnt("2");
        OneFragemnt fragment3 = new OneFragemnt("3");
        OneFragemnt fragment4 = new OneFragemnt("4");
        OneFragemnt fragment5 = new OneFragemnt("5");
        mList.add(fragment1);
        mList.add(fragment2);
        mList.add(fragment3);
        mList.add(fragment4);
        mList.add(fragment5);
        mPagerAdapter = new MyFragmentPagerAdapter<OneFragemnt>(getSupportFragmentManager(),mList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//              mTabView1.setTabViewDefaultPosition(position);
                mTabView1.setSelectedPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTabView() {
        List<TabViewChild> mList = new ArrayList<>();
        Fragment fragment = new Fragment();
        mList.add(new TabViewChild(R.drawable.tab01_sel, R.drawable.tab01_unsel, "1",fragment));
        mList.add(new TabViewChild(R.drawable.tab02_sel, R.drawable.tab02_unsel, "2",fragment));
        mList.add(new TabViewChild(R.drawable.tab03_sel, R.drawable.tab03_unsel, "3",fragment));
        mList.add(new TabViewChild(R.drawable.tab04_sel, R.drawable.tab04_unsel, "4",fragment));
        mList.add(new TabViewChild(R.drawable.tab05_sel, R.drawable.tab05_unsel, "5",fragment));

        mTabView.setTabViewChild(mList, getSupportFragmentManager());
        mTabView.setTabViewDefaultPosition(0);
        mTabView.findViewById(R.id.tabview_fragment_container).setVisibility(View.GONE);
        mTabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int position, ImageView imageView, TextView textView) {
                mViewPager.setCurrentItem(position);
            }
        });

    }

    private void initTabView1() {
        List<com.wmz.mylibrary.tabview.TabViewChild> mList = new ArrayList<>();
        Fragment fragment = new Fragment();
        mList.add(new com.wmz.mylibrary.tabview.TabViewChild(R.drawable.tab01_sel, R.drawable.tab01_unsel, "1"));
        mList.add(new com.wmz.mylibrary.tabview.TabViewChild(R.drawable.tab02_sel, R.drawable.tab02_unsel, "2"));
        mList.add(new com.wmz.mylibrary.tabview.TabViewChild(R.drawable.tab03_sel, R.drawable.tab03_unsel, "3"));
        mList.add(new com.wmz.mylibrary.tabview.TabViewChild(R.drawable.tab04_sel, R.drawable.tab04_unsel, "4"));
        mList.add(new com.wmz.mylibrary.tabview.TabViewChild(R.drawable.tab05_sel, R.drawable.tab05_unsel, "5"));

        mTabView1.setTabViewChild(mList);

        mTabView1.findViewById(R.id.tabview_fragment_container).setVisibility(View.GONE);
        mTabView1.setOnTabChildClickListener(new com.wmz.mylibrary.tabview.TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int position, ImageView imageView, TextView textView) {
                mViewPager.setCurrentItem(position);
            }
        });
        mTabView1.setTabViewDefaultPosition(2);
    }

    class OneFragemnt extends BaseFragment {
        public String msg = "0";

        public OneFragemnt(String msg) {
            this.msg = msg;
            setTitle(msg);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Button btn = new Button(getActivity());
            btn.setText(msg);
            return btn;
        }

        @Override
        public int getLayoutResourceId() {
            return 0;
        }


        @Override
        public void initView(View view) {

        }
    }
}
