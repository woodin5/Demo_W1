package com.wmz.mylibrary.network;


import com.wmz.mylibrary.manager.ExecutorManager;

/**
 * Created by wmz on 20/6/16.
 */
public class NetworkRequest {

    private volatile static NetworkRequest request;
    private NetworkRequest(){}
    public synchronized static NetworkRequest getInstance() {
        if (request == null) {
            synchronized (NetworkRequest.class) {
                if (request == null) {
                    request = new NetworkRequest();
                }
            }
        }
        return request;
    }

    /**
     * get方式请求网络
     * @param url
     * @param response
     */
    public void execute(final String url, final NetworkResponse response) {
        ExecutorManager.execute(new Runnable() {
            @Override
            public void run() {
                NetworkExecuter.executeByGet(url, response);
            }
        });
    }

    /**
     * post方式请求网络
     * @param url
     * @param params
     * @param response
     */
    public void execute(final String url, final String params,final NetworkResponse response){
        ExecutorManager.execute(new Runnable() {
            @Override
            public void run() {
                NetworkExecuter.executeByPost(url,params,response);
            }
        });
    }
}
