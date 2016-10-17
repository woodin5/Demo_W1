package com.wmz.demo_w1;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

public class AutoLayoutActivity extends com.zhy.autolayout.AutoLayoutActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setImmersionStatus();
        setContentView(R.layout.activity_auto_layout);


        initView();
        initDatas();
    }

    private void setImmersionStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void initDatas() {
        ArrayList<Fragment> mList = new ArrayList<Fragment>();
        mList.add(new ListFragment());
//        mList.add(new RegisterFragment());
//        mList.add(new PayFragment());
//        mList.add(new RecyclerViewFragment());
//        mList.add(new RecyclerViewGridFragment());
//        mList.add(new TestFragment());
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), mList));
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
    }




    public class MyAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> tabs = null;

        public MyAdapter(FragmentManager fm, ArrayList<Fragment> tabs) {
            super(fm);
            this.tabs = tabs;
        }

        @Override
        public Fragment getItem(int pos) {
            return tabs.get(pos);
        }

        @Override
        public int getCount() {
            return tabs.size();
        }
    }

    static class ListFragment extends Fragment
    {
        private View mView;
        private ListView mlistview;
        private List<String> mList;
        private Context mContext;

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
        {
            mView = inflater.inflate(R.layout.fragment_list, container, false);
            initView();
            return mView;
        }

        private void initView()
        {
            mContext = getActivity();
            mlistview = (ListView) mView.findViewById(R.id.id_listview);
            mList = new ArrayList<String>();
            for (int i = 0; i < 50; i++)
            {
                mList.add(i + "");
            }
            mlistview.setAdapter(new MyAdapter());

//        mlistview.setAdapter(new CommonAdapter<String>(getActivity(),R.layout.list_item,mList)
//        {
//            @Override
//            protected void onConvertViewCreated(View convertView)
//            {
//                AutoUtils.autoSize(convertView);
//            }
//
//            @Override
//            public void convert(com.zhy.base.mAdapter.ViewHolder viewHolder, String s)
//            {
//
//            }
//        });
        }

        class MyAdapter extends BaseAdapter
        {

            @Override
            public int getCount()
            {
                return mList.size();
            }

            @Override
            public Object getItem(int arg0)
            {
                return mList.get(arg0);
            }

            @Override
            public long getItemId(int position)
            {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                ViewHolder holder = null;
                if (convertView == null)
                {
                    holder = new ViewHolder();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
                    convertView.setTag(holder);
                    //对于listview，注意添加这一行，即可在item上使用高度
                    AutoUtils.autoSize(convertView);
                } else
                {
                    holder = (ViewHolder) convertView.getTag();
                }

                return convertView;
            }

        }

        class ViewHolder
        {

        }

    }
}
