package com.wmz.demo_w1.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.mylibrary.adapter.MyCommonAdapter;
import com.wmz.mylibrary.adapter.MyCommonRecyclerAdapter;
import com.wmz.mylibrary.adapter.RecyclerViewHolder;
import com.wmz.mylibrary.adapter.ViewHolder;
import com.wmz.mylibrary.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class XRecyclerViewActivity extends BaseActivity {
    private Context mContext = XRecyclerViewActivity.this;

    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_xrecycler_view;
    }

    @Override
    protected void initData() {
        super.initData();
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("i=" + i);
        }
        MyAdapter adapter = new MyAdapter(mContext, list);
        xRecyclerView.setAdapter(adapter);
        xRecyclerView.setLayoutManager(manager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotate);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xRecyclerView.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xRecyclerView.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    class MyAdapter extends MyCommonRecyclerAdapter<String> {

        final static int type_default = 1000;
        final static int type_grid = 1001;
        final static int type_list = 1002;
        private List<Integer> viewTypes = new ArrayList<>();

        protected MyAdapter(Context context, List<String> list) {
            super(context, list);
        }


        @Override
        protected int getItemLayoutId(int viewType) {
            if (viewType == type_grid) {
                return R.layout.item_recycler_view;
            } else if (viewType == type_list) {
                return R.layout.layout_linear;
            }
            return R.layout.item_card;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                viewTypes.add(position, type_grid);
                return type_grid;
            } else if (position == 1) {
                viewTypes.add(position, type_list);
                return type_list;
            }
            viewTypes.add(position, type_default);
            return super.getItemViewType(position);
        }

        @Override
        protected void bindData(RecyclerViewHolder holder, int position, Object item) {

            if (type_grid == viewTypes.get(position)) {
                showLayoutGridView(holder);
            } else if (type_list == viewTypes.get(position)) {
                showLayoutLinear(holder);
            } else {
                holder.setText(R.id.tv_card, item.toString());
            }
        }

        private void showLayoutLinear(RecyclerViewHolder holder) {
            String url = "http://img9.3lian.com/c1/vector/10/01/237.jpg";
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                list.add(url);
            }
            int position = 0;
            LinearLayout layout = holder.findViewById(R.id.layout_linear);
            for(int i=0;i<layout.getChildCount();i++){
                ImageView imageView = (ImageView) layout.getChildAt(i);
                imageView.setId(position++);
                imageView.setOnClickListener(new LinearOnClickListener());
            }
        }

        private void showLayoutGridView(RecyclerViewHolder holder) {
            String url = "http://img9.3lian.com/c1/vector/10/01/237.jpg";
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                list.add(url);
            }

//            LinearLayout layout = holder.findViewById(R.id.layout_grid);
//            int position = 0;
//            for (int i = 0; i < layout.getChildCount(); i++) {
//                LinearLayout layout1 = (LinearLayout) layout.getChildAt(i);
//                if(i%2==0) {
//                    for (int j = 0; j < layout1.getChildCount(); j++) {
//                        ImageView imageView = (ImageView) layout1.getChildAt(j);
//                        imageView.setId(position++);
//                        imageView.setOnClickListener(new GridOnClickListener());
//                        GlideUtils.into(mContext, R.drawable.shopping, url, imageView);
//                    }
//                }else{
//                    for (int j = 0; j < layout1.getChildCount(); j++) {
//                        TextView textView = (TextView) layout1.getChildAt(j);
//                        textView.setText("j="+j);
//                    }
//                }
//            }
            DisplayMetrics metric = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metric);

            int screenWidth = metric.widthPixels;
            int ONE_LINE_SHOW_NUMBER = 4;
            RecyclerView mRecyclerView = holder.findViewById(R.id.recyclerView);
            //每行显示4个，水平显示
            mRecyclerView.setLayoutManager(new GridLayoutManager(XRecyclerViewActivity.this, ONE_LINE_SHOW_NUMBER, LinearLayoutManager.VERTICAL, false));
            ViewGroup.LayoutParams params = mRecyclerView.getLayoutParams();
            int lineNumber = list.size()%ONE_LINE_SHOW_NUMBER==0?list.size()/ONE_LINE_SHOW_NUMBER:list.size()/ONE_LINE_SHOW_NUMBER+1;
            params.height = lineNumber*(screenWidth/4);//+(lineNumber-1)*dip2px(10);
            mRecyclerView.setLayoutParams(params);
            GridAdapter adapter = new GridAdapter(mContext,list);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setBackgroundColor(Color.BLUE);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int pos) {
                    Toast.makeText(mContext,"pos="+pos,Toast.LENGTH_SHORT).show();
                }
            });

        }
        /**
         * 将dp转化为px
         */
        private int dip2px(float dip) {
            float v = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
            return (int) (v + 0.5f);
        }
        class GridOnClickListener implements View.OnClickListener{

            @Override
            public void onClick(View v) {
             Toast.makeText(mContext,"grid="+v.getId(),Toast.LENGTH_SHORT).show();
            }
        }

        class LinearOnClickListener implements View.OnClickListener{

            @Override
            public void onClick(View v) {
             Toast.makeText(mContext,"linear="+v.getId(),Toast.LENGTH_SHORT).show();
            }
        }

        class LinearAdapter extends MyCommonAdapter<String> {
            private Context mContext;

            protected LinearAdapter(Context context, List<String> list) {
                super(context, list);
                mContext = context;
            }

            @Override
            protected void builderData(ViewHolder viewHolder, Object obj, int position) {
                ImageView imageView = viewHolder.findViewById(R.id.iv);
                GlideUtils.into(mContext, obj.toString(), imageView);
            }

            @Override
            protected int builderView(LayoutInflater inflater) {
                return R.layout.item_iv;
            }
        }

        class GridAdapter extends MyCommonRecyclerAdapter<String>{

            protected GridAdapter(Context context, List<String> list) {
                super(context, list);
            }

            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_iv_tv;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, Object item) {
               ImageView imageView =  holder.findViewById(R.id.iv);
//                GlideUtils.into(mContext,item.toString(),imageView);
            }
        }
    }
}
