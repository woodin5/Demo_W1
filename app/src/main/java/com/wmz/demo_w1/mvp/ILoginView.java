package com.wmz.demo_w1.mvp;

/**
 * Created by Administrator on 2016/11/7.
 */

public interface ILoginView extends IAbsView{
    //获取用户名
    String getUserName();
    //获取密码
    String getPassword();
    //用户名错误
    void onUsernameError();
    //密码错误
    void onPasswordError();
}
