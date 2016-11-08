package com.wmz.demo_w1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.wmz.demo_w1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SweetDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweet_dialog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_1)
    void showMsg() {
        SweetAlertDialog sd = new SweetAlertDialog(this);
        sd.show();
    }

    @OnClick(R.id.btn_2)
    void showMsgTitle() {
        SweetAlertDialog sd = new SweetAlertDialog(this);
        sd.setTitleText("title");
        sd.setContentText("Msg");
        sd.show();
    }

    @OnClick(R.id.btn_3)
    void showMsgProgress() {
        new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .show();
    }

    @OnClick(R.id.btn_4)
    void showError() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .show();
    }

    @OnClick(R.id.btn_5)
    void showSuccess() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .show();
    }

    @OnClick(R.id.btn_6)
    void showIcoon() {
        new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setCustomImage(R.drawable.android)
                .show();
    }

    @OnClick(R.id.btn_7)
    void show() {
        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.android);
        SweetAlertDialog sd = new SweetAlertDialog(this);
        sd.setContentView(img);
        sd.show();
    }
}
