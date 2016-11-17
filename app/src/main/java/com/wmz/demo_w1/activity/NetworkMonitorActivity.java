package com.wmz.demo_w1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.android.network.monitor.NetworkManager;
import com.android.network.monitor.NetworkObserver;
import com.android.network.monitor.NetworkType;
import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;

import butterknife.BindView;

public class NetworkMonitorActivity extends BaseActivity {

    @BindView(R.id.btn)
    Button mBtn;
//    private NetworkObserver observer = new NetworkObserver() {
//        @Override
//        public void onNetworkStateChanged(NetAction action) {
//            mBtn.setText(action.getType().toString());
//        }
//    };
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_network_monitor;
    }

    @Override
    public void onNetworkChanged(NetworkType netWorkType) {
        super.onNetworkChanged(netWorkType);
        mBtn.setText(netWorkType.toString());
    }

    //    @Override
//    protected void initEvent() {
//        super.initEvent();
//        NetworkManager.getInstance().initialized(this);
//        NetworkManager.getInstance().register(observer);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        NetworkManager.getInstance().unregister(observer);
//    }
}
