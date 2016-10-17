package com.wmz.demo_w1;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.wmz.demo_w1.secondaryscreen.DisplayManagers;

/**
 * Created by Mjg on 2016/6/2.
 */
public class DisplayManagerTools {
    private static String TAG = "DisplayManagerTools";

    /**
     * 隐藏底部导航栏
     */
    public static void hideSystemUI(Activity activity) {
        int flag = activity.getWindow().getDecorView().getSystemUiVisibility();

        int fullScreen = 0x8;

        if ((flag & fullScreen) != 0) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }

        if ((flag & fullScreen) == 0) {
            activity.getWindow().getDecorView().setSystemUiVisibility(flag | fullScreen);
        }
    }

    /**
     * 屏幕管理类
     */
    public static DisplayManagers displayManagers;

    /**
     * 获取 DisplayManagers 副屏显示
     **/
    public static void show(Context context) {
        if (displayManagers != null) {
            displayManagers.dismiss();
        }
        displayManagers = new DisplayManagers(context);
        displayManagers.show();
    }

    public static void releaseDispalyManager() {
        if (displayManagers != null) {
            displayManagers.dismiss();
        }
        displayManagers = null;
    }


    /***
     * 隐藏键盘
     */
    public static void dismissInputMethod(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


}
