package com.wmz.demo_w1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;


public class EventBus2Activity extends AppCompatActivity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus2);

        btn = (Button) findViewById(R.id.btn_first_event);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusMessageEvent("EventBus2Activity clicked",1));
            }
        });
    }
}
