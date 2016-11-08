package com.wmz.demo_w1.activity;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.ViewGroup;

import com.wmz.demo_w1.R;
import com.wmz.demo_w1.adapter.RefreshFootAdapter;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.mylibrary.adapter.MyCommonRecyclerAdapter;
import com.wmz.mylibrary.adapter.RecyclerViewHolder;

import java.util.ArrayList;

import butterknife.BindView;

public class NwSwipeRefreshLayoutActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    Context mContext = NwSwipeRefreshLayoutActivity.this;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_nw_swipe_refresh_layout;
    }

    @Override
    protected void initEvent() {

        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

        //添加下拉刷新监听器
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setOnScrollListener(new OnScrollListener());
    }

    MyCommonRecyclerAdapter<String> mAdapter = null;
    @Override
    protected void initData() {
        ArrayList<String> mList = new ArrayList<>();
        for(int i=0;i<20;i++){
            mList.add("hello_"+i);
        }
         mAdapter = new MyCommonRecyclerAdapter<String>(mContext,mList) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_card;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, Object item) {
                holder.setText(R.id.tv_card,item.toString());
            }

             @Override
             public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                 return super.onCreateViewHolder(parent, viewType);
             }
         };
//        recyclerView.setAdapter(mAdapter);
        recyclerView.setAdapter(new RefreshFootAdapter(mContext));
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, 1);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //显示或者隐藏刷新进度条
                swipeRefreshLayout.setRefreshing(false);
                mAdapter.delete(0);
                mAdapter.notifyDataSetChanged();
            }
        },1000);
    }

    int lastVisibleItem = 0;
    boolean isRefresh = false;
    boolean isLoadMore = false;
    class OnScrollListener extends RecyclerView.OnScrollListener{
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1== mAdapter.getItemCount()){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(isLoadMore) {
                            mAdapter.add(mAdapter.getItemCount(),"more");
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                },1000);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if(dy>0){
                isRefresh = false;
                isLoadMore = true;
            }else{
                isRefresh = true;
                isLoadMore = false;
            }
            lastVisibleItem  = ((GridLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        }

    }
}
