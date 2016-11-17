package com.wmz.demo_w1.activity;

import android.app.Activity;
import android.graphics.Color;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.tv_webView)
    TextView mTv;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initData() {
        super.initData();

        //设置编码记
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        //支持js
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setBackgroundColor(Color.argb(0, 0, 0, 0));

        mWebView.addJavascriptInterface(new JavaScriptObject(this, mWebView), "jsObj");
        mWebView.loadUrl("file:///android_asset/index.html");
    }

    @OnClick(R.id.appCallHtml)
    void appCallHtml(){
        String param = "AppCallHtml:appCallHtml(null)";
        mWebView.loadUrl("javascript: appCallHtml('')");
        mTv.setText(param);
    }

    @OnClick(R.id.appCallHtmlParam)
    void appCallHtmlParam(){
       String param = "AppCallHtml:appCallHtml(param)";
        mWebView.loadUrl("javascript: appCallHtml('"+param+"')");
        mTv.setText(param);
    }

    @OnClick(R.id.noParamAppCallHtmlReturnApp)
    void noParamAppCallHtmlReturnApp(){
        /**
         * App调html，Html处理，传回给APp
         */
        String param = "noParamAppCallHtmlReturnApp";
        mWebView.loadUrl("javascript: AppCallHtmlToApp('')");
        mTv.setText(param);
    }

    @OnClick(R.id.paramAppToHtmlReturnApp)
    void paramAppToHtmlReturnApp(){
        /**
         * App传给html参数，Html处理，传回给APp
         */
        String param = "paramAppToHtmlReturnApp";
        mWebView.loadUrl("javascript: AppCallHtmlToApp('"+param+"')");
        mTv.setText(param);
    }

    class JavaScriptObject {
        private Activity mContext;
        private WebView webView;


        public JavaScriptObject(Activity context, WebView webView) {
            this.mContext = context;
            this.webView = webView;

        }

        /**
         * Html传给App
         * @param param
         */
        @JavascriptInterface//1.7以上需要注解
        public void HtmlcallJava(final String param) {
            Toast.makeText(mContext, "HtmlCallApp:" + param, Toast.LENGTH_SHORT).show();
        }

        /**
         * Html传给App，App处理再传回给Html
         * @param param
         */
        @JavascriptInterface//1.7以上需要注解
        public void HtmlcallJava2(final String param) {
            mContext.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    webView.loadUrl("javascript: showFromHtml2('" + param + "2" + "')");
                }
            });


        }

        /**
         * App传给html参数，Html处理，传回给APp
         */
        @JavascriptInterface
        public void paramAppToHtmlToApp(final String param) {
            Toast.makeText(mContext, param, Toast.LENGTH_SHORT).show();

        }

    }

}
