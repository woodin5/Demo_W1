package com.wmz.mylibrary;

/**
 * Created by Administrator on 2016/9/3.
 */
public class EventBusMessageEvent {
    private String msg;
    private int type;
    public EventBusMessageEvent(String msg, int type) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public int getType() {
        return type;
    }
}
