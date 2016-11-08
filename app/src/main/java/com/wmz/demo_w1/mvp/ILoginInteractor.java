package com.wmz.demo_w1.mvp;

/**
 * Created by wmz on 2016/11/7.
 */

public interface ILoginInteractor {
    interface LoginResponse extends IAbsResponse {
        //用户名错误
        void onUsernameError();

        //密码错误
        void onPasswordError();
    }

    void login(String username, String password, LoginResponse response);
}
