package com.wmz.demo_w1.mvp;

import android.os.Handler;

/**
 * Created by wmz on 2016/11/7.
 */

public class LoginPresenterImp implements ILoginPresenter, ILoginInteractor.LoginResponse {
    private ILoginView mLoginView;
    private ILoginInteractor loginInteractor;
    private Handler mHandler = new Handler();

    public LoginPresenterImp(ILoginView loginView) {
        this.mLoginView = loginView;
        loginInteractor = new LoginInteractorImp();
    }

    @Override
    public void login() {
        loginInteractor.login(mLoginView.getUserName(), mLoginView.getPassword(), this);
    }

    @Override
    public void onUsernameError() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mLoginView.onUsernameError();
            }
        });
    }

    @Override
    public void onPasswordError() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mLoginView.onPasswordError();
            }
        });
    }

    @Override
    public void success(final Object obj) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mLoginView.success(obj);
            }
        });
    }

    @Override
    public void failed(final String msg) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mLoginView.failed(msg);
            }
        });
    }

}
