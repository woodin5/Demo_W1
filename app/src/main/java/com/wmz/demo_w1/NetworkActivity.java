package com.wmz.demo_w1;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.demo_w1.base.BaseFragment;
import com.wmz.demo_w1.bean.Weather;
import com.wmz.mylibrary.EventBusMessage;
import com.wmz.mylibrary.adapter.MyFragmentPagerAdapter;
import com.wmz.mylibrary.adapter.ViewHolder;
import com.wmz.mylibrary.helper.OkHttpHelper;
import com.wmz.mylibrary.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NetworkActivity extends BaseActivity {

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
            String url = "http://api.map.baidu.com/telematics/v3/weather?location=深圳&output=json&ak=UMs5TPhtIKtzxG6RQz2QcSPs";
            OkHttpHelper.getInstance().enqueue(url, new Callback() {
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
