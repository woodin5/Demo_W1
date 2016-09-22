package com.wmz.demo_w1;


import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EventBusActivity extends BaseActivity {
    @BindView(R.id.btn_try)
    Button btn;
    @BindView(R.id.tv)
    TextView tv;

    public final static int FROM_ACTIVITY = 0;
    public final static int FROM_FRAGMENT = 1;



    @Override
    protected void init() {
        super.init();
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new EventBusFragment()).commit();
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.btn_try)
    void send() {
        Toast.makeText(EventBusActivity.this,"onClick",Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new EventBusMessageEvent("from activity", FROM_ACTIVITY));
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_eventbus;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void handleEventBus(com.wmz.mylibrary.EventBusMessageEvent event) {
        super.handleEventBus(event);
        if (event.getType() == FROM_FRAGMENT) {
            String msg = "activity收到fragment消息：" + event.getMsg();
//            tv.setText(msg);
            Toast.makeText(EventBusActivity.this, msg, Toast.LENGTH_LONG).show();
        }
    }
}
