package com.wmz.demo_w1.activity;

import android.widget.Button;
import android.widget.Toast;

import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.demo_w1.base.BaseFragment;
import com.wmz.mylibrary.EventBusMessage;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;


public class EventBusActivity extends BaseActivity {
    @BindView(R.id.btn)
    Button btn;

    public final static int FROM_ACTIVITY = 0;
    public final static int FROM_FRAGMENT = 1;

    @Override
    protected void init() {
        super.init();
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new MyEventBusFragment()).commit();
    }


    @OnClick(R.id.btn)
    void send() {
        EventBus.getDefault().post(new EventBusMessage("activity send -"+System.currentTimeMillis(), FROM_ACTIVITY));
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_eventbus;
    }

    @Override
    public void handleEventBus(EventBusMessage event) {
        super.handleEventBus(event);
        if (event.getType() == FROM_FRAGMENT) {
            btn.setText(event.getT().toString());
            Toast.makeText(EventBusActivity.this, event.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    static class MyEventBusFragment extends BaseFragment {

        @BindView(R.id.btn)
        Button btn;

        @Override
        public int getLayoutResourceId() {
            return R.layout.item_btn;
        }

        @OnClick(R.id.btn)
        public void onSend() {
            EventBus.getDefault().post(new EventBusMessage("fragment send - "+ System.currentTimeMillis(), EventBusActivity.FROM_FRAGMENT,"t=1"));
        }

        @Override
        public void handleEventBus(EventBusMessage event) {
            super.handleEventBus(event);
            if (event.getType() == EventBusActivity.FROM_ACTIVITY) {
                btn.setText(event.getMsg());
                Toast.makeText(getContext(), event.getMsg(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
