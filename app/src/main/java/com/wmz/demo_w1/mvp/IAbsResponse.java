package com.wmz.demo_w1.mvp;

/**
 * Created by wmz on 2016/11/7.
 */

public interface IAbsResponse {
    //成功方法
    void success(Object obj);
    //失败方法
    void failed(String msg);
}
