package com.wmz.demo_w1.activity;

import android.support.annotation.BinderThread;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.demo_w1.base.BaseFragment;
import com.wmz.demo_w1.bean.Weather;
import com.wmz.mylibrary.EventBusMessage;
import com.wmz.mylibrary.adapter.MyFragmentPagerAdapter;
import com.wmz.mylibrary.adapter.ViewHolder;
import com.wmz.mylibrary.network.OkHttpRequest;
import com.wmz.mylibrary.utils.GsonUtils;
import com.wmz.mylibrary.utils.NetUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NetworkActivity extends BaseActivity {

    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.tv)
    TextView mTv;

    @BindView(R.id.network_viewpager)
    ViewPager mViewPager;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_network;
    }

    @Override
    protected void initView() {
        super.initData();
        initViewPager();
    }
    @OnClick(R.id.btn)
    void onClick(){
        if(NetUtils.hasNetwork(mContext)){
            String networkType = NetUtils.getNetworkType(mContext);
            showToast("networkType="+networkType);
        }else{
            showToast("not net");
        }
        mBtn.setText("start request");
        String url = "http://api.map.baidu.com/telematics/v3/weather?location=深圳&output=json&ak=UMs5TPhtIKtzxG6RQz2QcSPs";
        OkHttpRequest.getInstance().enqueue(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                Weather weather = (Weather) GsonUtils.jsonToObject(content,Weather.class);
                EventBus.getDefault().post(new EventBusMessage<Weather>(0,weather));
            }
        });
    }

    private void initViewPager() {
        ArrayList<MyFragment> mList = new ArrayList<>();
        mList.add(new MyFragment());
        MyFragmentPagerAdapter<MyFragment> adapter = new MyFragmentPagerAdapter<MyFragment>(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(adapter);
    }


    static class MyFragment extends BaseFragment {
        @BindView(R.id.tv)
        TextView mTv;

        @Override
        protected int getLayoutResourceId() {
            return R.layout.layout_show_response;
        }

        @Override
        protected void builderData(ViewHolder viewHolder) {
            viewHolder.setText(R.id.btn, "request");
            viewHolder.setText(R.id.tv, "response");
        }

        @OnClick(R.id.btn)
        void send() {
            showProgress("loading...");
            okHttpRequest();
        }

        private void okHttpRequest() {
            String url = "http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=UMs5TPhtIKtzxG6RQz2QcSPs";
            OkHttpRequest.getInstance().enqueue(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String content = response.body().string();
                    Log.e("", "wmz:content=" + content);
                    Weather weather = (Weather) GsonUtils.jsonToObject(content, Weather.class);
                    EventBus.getDefault().post(new EventBusMessage<Weather>(0,weather));
                }
            });
        }

        @Override
        public void handleEventBus(EventBusMessage message) {
            super.handleEventBus(message);
            dismissProgress();
            if(message.getType()==0){
                Weather weather = (Weather) message.getT();
                mTv.setText(weather.getResults().get(0).getCurrentCity());
            }
        }
    }
}
