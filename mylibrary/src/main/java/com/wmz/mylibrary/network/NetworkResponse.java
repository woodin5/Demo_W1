package com.wmz.mylibrary.network;

/**
 * Created by wmz on 20/6/16.
 */
public abstract class NetworkResponse {

    public NetworkResponse(){}
    public abstract void onResponse(String response);

    public abstract void onError(String error);
}
