package com.wmz.demo_w1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Administrator on 2016/9/5.
 */
public class EventBusFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Button btn = new Button(getContext());
        btn.setText("click");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"f-send",Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new EventBusMessageEvent("EventBusFragment onclick",EventBusActivity.FROM_FRAGMENT));
            }
        });
        return btn;
    }

    @Subscribe
    public void handleEventMessage(EventBusMessageEvent event){
        if(event.getType()==EventBusActivity.FROM_ACTIVITY){
            String msg = "handleEventMessage收到了消息：" + event.getMsg();
            Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
