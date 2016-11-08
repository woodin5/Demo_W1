package com.wmz.demo_w1.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.demo_w1.base.BaseFragment;
import com.wmz.mylibrary.adapter.MyFragmentPagerAdapter;
import com.wmz.mylibrary.adapter.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;

public class NwTabLayoutActivity extends BaseActivity {

    @BindView(R.id.nw_tablayout)
    TabLayout tabLayout;

    @BindView(R.id.nw_tablayout_viewpager)
    ViewPager viewPager;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_nw_tab_layout;
    }

    @Override
    protected void initData() {
        String[] arrs = getResources().getStringArray(R.array.list);
        mList.add(new MyFragment("1"));
        mList.add(new MyFragment("2"));
        mList.add(new MyFragment("3"));
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter<MyFragment>(getSupportFragmentManager(),mList,arrs);
        viewPager.setAdapter(adapter);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabsFromPagerAdapter(mAdapter);
    }

    ArrayList<MyFragment> mList = new ArrayList<>();
    static class MyFragment extends BaseFragment{
        String msg = "0";
        public MyFragment(String msg){
            this.msg = msg;
        }
        @Override
        protected int getLayoutResourceId() {
            return R.layout.item_btn;
        }

        @Override
        protected void builderData(ViewHolder viewHolder) {
            viewHolder.setText(R.id.btn,msg);
        }
    }
}
