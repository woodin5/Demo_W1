package com.wmz.demo_w1.mvp;

/**
 * Created by wmz on 2016/11/7.
 */

public interface IAbsView<T> {
    void showProgress();
    void hideProgress();
    void success(T t);
    void failed(String msg);
}
