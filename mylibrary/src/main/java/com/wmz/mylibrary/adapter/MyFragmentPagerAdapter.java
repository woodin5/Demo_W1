package com.wmz.mylibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmz on 2016/9/9.
 */
public class MyFragmentPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> mList = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyFragmentPagerAdapter(FragmentManager fm, List<T> list) {
        super(fm);
        mList = list;
    }
    public MyFragmentPagerAdapter(FragmentManager fm, List<T> list, List<String> titles) {
        super(fm);
        mList = list;
        mTitles = titles;
    }
    public void setmTitles(List<String> mTitles) {
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.size()==0?null:mTitles.get(position).toString();
    }
}
