package com.wmz.mylibrary.network;

import android.os.Handler;
import android.os.Message;

import com.wmz.mylibrary.manager.ExecutorManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;


public class OkHttpRequest {
    private volatile static OkHttpRequest instance;

    private OkHttpRequest() {
    }

    public static OkHttpRequest getInstance() {
        if (instance == null) {
            synchronized (OkHttpRequest.class) {
                if (instance == null) {
                    instance = new OkHttpRequest();
                }
            }
        }
        return instance;
    }

    /**
     * 执行get请求
     */
    public void execute(final String url, final Handler handler, final int what) {
        ExecutorManager.execute(new Runnable() {
            @Override
            public void run() {
                handler.sendMessage(Message.obtain(handler, what, execute(url)));
            }
        });
    }

    /**
     * 执行post请求
     */
    public void execute(final String url, final RequestBody body, final Handler handler, final int what) {
        ExecutorManager.execute(new Runnable() {
            @Override
            public void run() {
                handler.sendMessage(Message.obtain(handler, what, execute(url, body)));
            }
        });
    }

    /**
     * 执行get请求
     *
     * @param url
     * @param response
     */
    public void execute(final String url, final Response response) {
        ExecutorManager.execute(new Runnable() {
            @Override
            public void run() {
                response.onResponse(execute(url));
            }
        });
    }


    /**
     * 执行post请求
     *
     * @param url
     * @param body
     * @param response
     */
    public void execute(final String url, final RequestBody body, final Response response) {
        ExecutorManager.execute(new Runnable() {
            @Override
            public void run() {
                response.onResponse(execute(url, body));
            }
        });
    }

    /**
     * 执行get请求
     *
     * @param url
     * @return
     */
    public String execute(final String url) {
        return OkHttpExecutor.getInstance().execute(url);
    }

    /**
     * 执行post请求
     *
     * @param url
     * @param body
     * @return
     */
    public String execute(String url, RequestBody body) {
        return OkHttpExecutor.getInstance().execute(url, body);
    }

    public void enqueue(String url, Callback responseCallback) {
        OkHttpExecutor.getInstance().enqueue(url, responseCallback);
    }


    /**
     * 请求响应
     */
    public interface Response {
        void onResponse(String response);
    }

    /**
     * 请求体数据
     *
     * @param params
     * @return
     */
    public RequestBody getRequestBody(HashMap<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            builder.add(key, value);
        }
        return builder.build();
    }

    /**
     * 取消请求
     */
    public void cancelAll() {
        OkHttpExecutor.getInstance().cancelAll();
    }
}
