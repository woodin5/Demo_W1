package com.wmz.demo_w1.mvp;

import android.text.TextUtils;

import com.wmz.mylibrary.network.NetworkRequest;
import com.wmz.mylibrary.network.NetworkResponse;
import com.wmz.mylibrary.utils.GsonUtils;

/**
 * Created by wmz on 2016/11/7.
 */

public class LoginInteractorImp implements ILoginInteractor {


    @Override
    public void login(String username, String password, final LoginResponse loginResponse) {
        if(TextUtils.isEmpty(username)){
            loginResponse.onUsernameError();
            return;
        }
        if(TextUtils.isEmpty(password)){
            loginResponse.onPasswordError();
            return;
        }
        String url = "http://192.168.0.8:9090/api/index/login";
        NetworkRequest.getInstance().execute(url, new NetworkResponse() {
            @Override
            public void onResponse(String response) {
                LoginBean bean = (LoginBean) GsonUtils.jsonToObject(response,LoginBean.class);
                loginResponse.success(bean);
            }

            @Override
            public void onError(String error) {
                loginResponse.failed(error);
            }
        });
    }
}
