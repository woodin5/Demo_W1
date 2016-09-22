package com.wmz.mylibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wmz on 2016/9/9.
 */
public abstract class BaseFragment extends Fragment {

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView==null){
            mRootView = inflater.inflate(getLayoutResourceId(),container,false);
            initView(mRootView);
        }
        ViewGroup parent = (ViewGroup) container.getParent();
        if(parent!=null){
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRootView != null && mRootView.getParent() != null) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }

    public abstract int getLayoutResourceId();
    public abstract void initView(View view);
}
