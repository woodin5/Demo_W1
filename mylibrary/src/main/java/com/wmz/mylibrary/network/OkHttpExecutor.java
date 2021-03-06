package com.wmz.mylibrary.network;

import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by wmz on 26/5/16.
 */
public class OkHttpExecutor {
    private volatile static OkHttpExecutor request;
    private static OkHttpClient client = new OkHttpClient();

    public static void init() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                .addInterceptor(new LoggerInterceptor("TAG"))
//                .cookieJar(cookieJar1)
//                .sslSocketFactory(HttpsUtils.getSslSocketFactory(null, null, null))
                .build();
    }

    private OkHttpExecutor(){
        if(client==null){
            client = new OkHttpClient();
        }
    }

    public synchronized static OkHttpExecutor getInstance() {
        if (request == null) {
            synchronized (OkHttpExecutor.class) {
                if (request == null) {
                    request = new OkHttpExecutor();
                }
            }
        }
        return request;
    }

    public static OkHttpClient getClient() {
        return client;
    }

    public void setReadTimeout(long timeout) {
        client = client.newBuilder().readTimeout(timeout, TimeUnit.SECONDS).build();
    }

    /**
     * get
     * @param url
     * @return
     */
    public String execute(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            return client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post
     * @param url
     * @param body
     * @return
     */
    public String execute(String url, RequestBody body) {
        Log.e("wmz","url="+url);
        Log.e("wmz","body="+body.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(url)
                .build();
        try {
            return client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void enqueue(String url, Callback responseCallback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(responseCallback);
    }

    public void enqueue(String url, RequestBody body, Callback responseCallback) {
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(responseCallback);
    }

    public void cancelAll(){
        client.dispatcher().cancelAll();
    }

    public void cancel(){
    }
}
