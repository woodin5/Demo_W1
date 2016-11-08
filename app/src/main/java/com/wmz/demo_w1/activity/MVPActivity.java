package com.wmz.demo_w1.activity;

import android.widget.Button;
import android.widget.EditText;

import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.demo_w1.mvp.ILoginView;
import com.wmz.demo_w1.mvp.ILoginPresenter;
import com.wmz.demo_w1.mvp.LoginPresenterImp;

import butterknife.BindView;
import butterknife.OnClick;

public class MVPActivity extends BaseActivity implements ILoginView{
    @BindView(R.id.et_mvp_username)
    EditText mEtUsername;
    @BindView(R.id.et_mvp_password)
    EditText mEtPassword;
    @BindView(R.id.btn_mvp_confirm)
    Button mBtnConfirm;

    private ILoginPresenter mLoginPresenter;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void initData() {
        super.initData();
        mLoginPresenter = new LoginPresenterImp(this);
    }

    @OnClick(R.id.btn_mvp_confirm)
    void confirm(){
        mLoginPresenter.login();
    }

    @Override
    public String getUserName() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void onUsernameError() {
        showToast("username error");
    }

    @Override
    public void onPasswordError() {
        showToast("password error");
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void success(Object bean) {
        showToast(bean.toString());
    }

    @Override
    public void failed(String msg) {
        showToast("failed");
    }


}
