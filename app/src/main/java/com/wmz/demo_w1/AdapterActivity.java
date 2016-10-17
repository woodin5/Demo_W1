package com.wmz.demo_w1;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wmz.demo_w1.bean.User;
import com.wmz.mylibrary.adapter.BaseRecyclerAdapter;
import com.wmz.mylibrary.adapter.MyCommonAdapter;
import com.wmz.mylibrary.adapter.MyCommonRecyclerAdapter;
import com.wmz.mylibrary.adapter.RecyclerViewHolder;
import com.wmz.mylibrary.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AdapterActivity extends com.wmz.demo_w1.base.BaseActivity {

    @BindView(R.id.adapter_listview)
    ListView mLv;

    @BindView(R.id.adapter_gridview)
    GridView mGv;

    @BindView(R.id.adapter_recyclerView)
    RecyclerView mRecyclerView;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_adapter;
    }


    @OnClick(R.id.btn_showString)
     void showString() {
        ArrayList<String> mList = new ArrayList<>();
        for(int i=0;i<10;i++){
            mList.add("helle_"+i);
        }
        MyCommonAdapter<String> mAdapter = new MyCommonAdapter<String>(AdapterActivity.this, mList) {
            @Override
            protected void builderData(ViewHolder viewHolder, Object obj, int position) {
                viewHolder.setText(android.R.id.text1, obj.toString());
            }

            @Override
            protected int builderView(LayoutInflater inflater) {
                return android.R.layout.simple_list_item_1;
            }
        };

        mLv.setAdapter(mAdapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AdapterActivity.this,"p-string="+position,Toast.LENGTH_SHORT).show();
            }
        });
        mGv.setAdapter(mAdapter);
    }

    @OnClick(R.id.btn_showBean)
    void showBean(){
        ArrayList<User> mList = new ArrayList<>();
        for(int i=0;i<5;i++){
            User user = new User("hello_"+i);
            mList.add(user);
        }
        MyCommonAdapter<User> mAdapter = new MyCommonAdapter<User>(AdapterActivity.this, mList) {
            @Override
            protected void builderData(ViewHolder viewHolder, Object obj, int position) {
                viewHolder.setText(R.id.tv,((User)obj).getName());
                if(position%2==0){
                    viewHolder.setImageResource(R.id.img,R.drawable.ic_launcher);
                }
                viewHolder.setOnClickListener(R.id.btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AdapterActivity.this,"onclick",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            protected int builderView(LayoutInflater inflater) {
                return R.layout.item_bean;
            }
        };
        mLv.setAdapter(mAdapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AdapterActivity.this,"p-bean="+position,Toast.LENGTH_SHORT).show();
            }
        });
        mGv.setAdapter(mAdapter);
    }


    @OnClick(R.id.btn_showHorizontal)
    void showHorizontal(){
        ArrayList<String> mList = new ArrayList<>();
        for(int i=0;i<15;i++){
            mList.add("hello_"+i);
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AdapterActivity.this, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new RecyclerAdapter(mList));
    }

    @OnClick(R.id.btn_showVertical)
    void showVertical(){
        ArrayList<String> mList = new ArrayList<>();
        for(int i=0;i<15;i++){
            mList.add("hello_"+i);
        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(AdapterActivity.this,3, GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        MyRecyclerAdapter<String> mAdapter = new MyRecyclerAdapter(AdapterActivity.this,mList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Toast.makeText(AdapterActivity.this,"pos="+pos,Toast.LENGTH_SHORT).show();
            }
        });
    }


    class MyRecyclerAdapter<String> extends BaseRecyclerAdapter{
        public MyRecyclerAdapter(Context ctx, List list) {
            super(ctx, list);
        }

        @Override
        protected int getItemLayoutId(int viewType) {
            return R.layout.item_card;
        }

        @Override
        protected void bindData(com.wmz.mylibrary.adapter.RecyclerViewHolder holder, int position, Object item) {
            holder.setText(R.id.tv,item.toString());
            holder.setOnClickListener(R.id.btn, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AdapterActivity.this,"btn onclick",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }
    }


    class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerViewHolder>{
        private List<String> mList;
        public RecyclerAdapter(List<String> list){
            mList = list;
        }
        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,parent,false);
            RecyclerViewHolder holder = new RecyclerViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.text.setText(mList.get(position  ));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView text;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
