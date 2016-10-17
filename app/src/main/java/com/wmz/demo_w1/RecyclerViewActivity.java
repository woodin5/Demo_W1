package com.wmz.demo_w1;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.mylibrary.adapter.BaseRecyclerAdapter;
import com.wmz.mylibrary.adapter.MyCommonAdapter;
import com.wmz.mylibrary.adapter.MyCommonRecyclerAdapter;
import com.wmz.mylibrary.adapter.RecyclerViewHolder;
import com.wmz.mylibrary.adapter.ViewHolder;
import com.wmz.mylibrary.view.HorizontalListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class RecyclerViewActivity extends BaseActivity {

    private Context mContext = RecyclerViewActivity.this;
    ArrayList<String> mList = new ArrayList<>();
    MyCommonRecyclerAdapter<String> mAdapter = null;
    MyStaggeredRecyclerAdapter<String> mStaggeredAdapter = null;

    @BindView(R.id.horizontal_lv)
    HorizontalListView mHlv;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void initData() {
        String[] arrs = getResources().getStringArray(R.array.recyclerview_list);
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(arrs));
        mHlv.setAdapter(new MyCommonAdapter<String>(RecyclerViewActivity.this, list) {
            @Override
            protected void builderData(ViewHolder viewHolder, Object obj, int position) {
                viewHolder.setText(R.id.btn, obj.toString());
            }

            @Override
            protected int builderView(LayoutInflater inflater) {
                return R.layout.item_btn;
            }
        });
        mHlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showRecyclerView(position);
            }
        });

        initAdapter();
    }

    private void showRecyclerView(int position) {
        switch (position) {
            case 0:
                showLinearVertical();
                break;
            case 1:
                showLinearHorizontal();
                break;
            case 2:
                showGridVertical();
                break;
            case 3:
                showGridHorizontal();
                break;
            case 4:
                showStaggeredGridVertical();
                break;
            case 5:
                showStaggeredGridHorizontal();
                break;
            case 6:
                break;
        }
    }

    @Override
    protected void initEvent() {
     /*   //默认的动画效果
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分隔线
        mRecyclerView.addItemDecoration(new RvItemDecoration(this, OrientationHelper.VERTICAL));*/
    }

    public void initAdapter() {
        for (int i = 0; i < 30; i++) {
            mList.add("hello_" + i);
        }

        mAdapter = new MyRecyclerAdapter<>(mContext,mList);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Toast.makeText(mContext, "pos=" + pos, Toast.LENGTH_SHORT).show();
            }
        });

        mStaggeredAdapter = new MyStaggeredRecyclerAdapter<>(mContext,mList);
        mStaggeredAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Toast.makeText(mContext, "pos=" + pos, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showLinearVertical() {
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public void showLinearHorizontal() {
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public void showGridVertical() {
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public void showGridHorizontal() {
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public void showStaggeredGridVertical() {
        mRecyclerView.setAdapter(mStaggeredAdapter);
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public void showStaggeredGridHorizontal() {
//        mRecyclerView.setAdapter(mStaggeredAdapter);
//        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
//        mRecyclerView.setLayoutManager(mLayoutManager);
    }



    /**
     * 正常布局
     * @param <String>
     */
    class MyRecyclerAdapter<String> extends MyCommonRecyclerAdapter<String>{

        protected MyRecyclerAdapter(Context context, List<String> list) {
            super(context, list);
        }
        @Override
        protected int getItemLayoutId(int viewType) {
            return R.layout.item_card;
        }

        @Override
        protected void bindData(com.wmz.mylibrary.adapter.RecyclerViewHolder holder, int position, Object item) {
            holder.setText(R.id.tv_card, item.toString());
            holder.setOnClickListener(R.id.btn_card, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "btn onclick", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 瀑布流布局
     * @param <String>
     */
    class MyStaggeredRecyclerAdapter<String> extends MyCommonRecyclerAdapter<String>{
        int heighs[];
        protected MyStaggeredRecyclerAdapter(Context context, List<String> list) {
            super(context, list);
            heighs = new int[mList.size()];
            for (int i = 0; i < heighs.length; i++) {
                heighs[i] = (int) (Math.random() * 300 + 100);
            }
        }

        @Override
        protected int getItemLayoutId(int viewType) {
            return R.layout.item_card;
        }

        @Override
        protected void bindData(com.wmz.mylibrary.adapter.RecyclerViewHolder holder, int position, Object item) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            layoutParams.height = heighs[position];
            holder.itemView.setLayoutParams(layoutParams);
            holder.setText(R.id.tv_card, item.toString());
            holder.setOnClickListener(R.id.btn_card, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "btn onclick", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
