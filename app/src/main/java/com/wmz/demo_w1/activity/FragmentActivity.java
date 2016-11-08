package com.wmz.demo_w1.activity;

import android.support.v4.view.ViewPager;

import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.demo_w1.base.BaseFragment;
import com.wmz.mylibrary.adapter.MyFragmentPagerAdapter;
import com.wmz.mylibrary.adapter.ViewHolder;
//import com.ycl.tabview.library.TabView;
//import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;

import butterknife.BindView;

public class FragmentActivity extends BaseActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_fragment;
    }


    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected void initData() {
        ArrayList<MainFragment> mList = new ArrayList<>();
        mList.add(new MainFragment("1"));
        mList.add(new MainFragment("2"));
        mViewPager.setAdapter(new MyFragmentPagerAdapter<MainFragment>(getSupportFragmentManager(),mList));
    }

    public static class MainFragment extends BaseFragment{
        private String msg = "0";

        @BindView(R.id.viewPager)
        ViewPager mViewPager;

        public MainFragment(String msg){
            this.msg = msg;
        }

        @Override
        protected int getLayoutResourceId() {
            return R.layout.layout_viewpager;
        }

        @Override
        protected void initData() {
            ArrayList<BaseFragment> mList = new ArrayList<>();
            mList.add(new OneFragment("11"));
            mList.add(new TwoFragment("12"));
            mViewPager.setAdapter(new MyFragmentPagerAdapter<BaseFragment>(getActivity().getSupportFragmentManager(),mList));

        }

    }
    public static class OneFragment extends BaseFragment{
        private String msg = "0";
        public OneFragment(String msg){
            this.msg = msg;
        }

        @Override
        protected int getLayoutResourceId() {
            return R.layout.item_btn;
        }

        @Override
        protected void builderData(ViewHolder viewHolder) {
            super.builderData(viewHolder);
            viewHolder.setText(R.id.btn,msg);
        }
    }

    public static class TwoFragment extends BaseFragment{
        private String msg = "0";
        public TwoFragment(String msg){
            this.msg = msg;
        }

        @Override
        protected int getLayoutResourceId() {
            return R.layout.item_btn;
        }

        @Override
        protected void builderData(ViewHolder viewHolder) {
            super.builderData(viewHolder);
            viewHolder.setText(R.id.btn,msg);
        }
    }
}
