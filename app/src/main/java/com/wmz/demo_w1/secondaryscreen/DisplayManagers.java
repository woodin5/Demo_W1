package com.wmz.demo_w1.secondaryscreen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.WindowManager;

public class DisplayManagers {

    private Context mContext;

    private Activity mActivity;

    private DifferentDisplay mDDisplay;
    /**
     * 当前连接的显示器个数
     */
    private Display[] displays;

    public DisplayManagers(Context context) {
        mContext = context;
        mActivity = (Activity) context;
        init();
    }

    @SuppressLint("NewApi")
    private void init() {
        DisplayManager mDispayManager = (DisplayManager) mActivity.getSystemService(Context.DISPLAY_SERVICE);
        displays = mDispayManager.getDisplays();

        mDDisplay = new DifferentDisplay(mContext, displays[1]); //数组 0主 1副
//        mDDisplay = new DifferentDisplay(mContext, mActivity, displays[1], layoutId); //数组 0主 1副
        //设置布局类型
        mDDisplay.getWindow().setType(
                WindowManager.LayoutParams.FLAG_SECURE);
    }

    @SuppressLint("NewApi")
    public void show() {
        // 在当前 绑定的 屏幕显示 界面
        if (mDDisplay != null)
            mDDisplay.show();
    }


    @SuppressLint("NewApi")
    public void dismiss() {
        if (mDDisplay != null && mDDisplay.isShowing())
            mDDisplay.dismiss();
    }


}
