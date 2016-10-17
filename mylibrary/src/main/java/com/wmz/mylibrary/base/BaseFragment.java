package com.wmz.mylibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wmz.mylibrary.EventBusMessage;
import com.wmz.mylibrary.adapter.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by wmz on 2016/9/9.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    private View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView==null){
            mRootView = inflater.inflate(getLayoutResourceId(),container,false);
            init();
            initView(mRootView);
            initData();
            ViewHolder viewHolder = new ViewHolder(mRootView);
            builderData(viewHolder);
        }
        ViewGroup parent = (ViewGroup) container.getParent();
        if(parent!=null){
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRootView != null && mRootView.getParent() != null) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    /**填充数据
     * @param viewHolder
     */
    protected abstract void builderData(ViewHolder viewHolder);

    /**
     * 设置布局文件
     * @return
     */
    protected abstract int getLayoutResourceId();

    /**
     * 初始化View之前做的事
     */
    protected void init() {
    }

    /**
     * 初始化空控件
     */
    protected void initView(View view){}

    /**
     * 初始化数据
     */
    protected  void initData(){}

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEventBus(EventBusMessage message){
    }

    @Override
    public void onClick(View v) {
    }
}
