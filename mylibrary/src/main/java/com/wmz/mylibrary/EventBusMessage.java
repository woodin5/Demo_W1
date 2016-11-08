package com.wmz.mylibrary;

import android.os.Message;

/**
 * Created by wmz on 2016/9/3.
 */
public class EventBusMessage<T> {
    private String msg;
    private int type;
    private T t;

    public EventBusMessage(String msg, int type) {
        this.msg = msg;
        this.type = type;
    }

    public EventBusMessage(int type, T t) {
        this.type = type;
        this.t = t;
    }

    public EventBusMessage(String msg, int type, T t) {
        this.msg = msg;
        this.type = type;
        this.t = t;
    }

    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
