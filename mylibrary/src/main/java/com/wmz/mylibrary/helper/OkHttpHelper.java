package com.wmz.mylibrary.helper;

import android.os.Handler;
import android.os.Message;

import com.wmz.mylibrary.manager.ExecutorManager;
import com.wmz.mylibrary.network.OkHttpRequest;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;


public class OkHttpHelper{
    private volatile static OkHttpHelper instance;
    private OkHttpHelper(){
    }
    public static OkHttpHelper getInstance() {
        if(instance==null){
            synchronized (OkHttpHelper.class){
                if(instance==null){
                    instance = new OkHttpHelper();
                }
            }
        }
        return instance;
    }

    public void execute(final String url, final Handler handler,final int what){
        ExecutorManager.execute(new Runnable() {
            @Override
            public void run() {
                handler.sendMessage(Message.obtain(handler, what, execute(url)));
            }
        });
    }

    /**
     * 执行get请求
     * @param url
     * @param response
     */
    public void execute(final String url, final Response response){
        ExecutorManager.execute(new Runnable() {
            @Override
            public void run() {
                response.onResponse(execute(url));
            }
        });
    }



    /**
     * 执行post请求
     * @param url
     * @param body
     * @param response
     */
    public void execute(final String url, final RequestBody body,final Response response){
        ExecutorManager.execute(new Runnable() {
            @Override
            public void run() {
                response.onResponse(execute(url, body));
            }
        });
    }

    /**
     * 执行get请求
     * @param url
     * @return
     */
    public String execute(final String url){
        return OkHttpRequest.getInstance().execute(url);
    }

    /**
     * 执行post请求
     * @param url
     * @param body
     * @return
     */
    public String execute(String url, RequestBody body){
        return OkHttpRequest.getInstance().execute(url,body);
    }

    public void enqueue(String url, Callback responseCallback){
        OkHttpRequest.getInstance().enqueue(url,responseCallback);
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
}
