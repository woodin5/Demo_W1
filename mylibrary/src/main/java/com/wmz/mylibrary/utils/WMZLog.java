package com.wmz.mylibrary.utils;

import android.util.Log;

/**
 * Created by wmz on 2016/11/8.
 */

public class WMZLog {
    public static boolean debugMode = false;

    public WMZLog() {
    }

    public static void d(String var0, String var1) {
        if(var1 != null) {
            if(debugMode) {
                Log.d(var0, var1);
            }

        }
    }

    public static void e(String var0, String var1) {
        if(var1 != null) {
            if(debugMode) {
                Log.e(var0, var1);
            }

        }
    }

    public static void i(String var0, String var1) {
        if(var1 != null) {
            if(debugMode) {
                Log.i(var0, var1);
            }

        }
    }

    public static void v(String var0, String var1) {
        if(var1 != null) {
            if(debugMode) {
                Log.v(var0, var1);
            }

        }
    }

    public static void w(String var0, String var1) {
        if(var1 != null) {
            if(debugMode) {
                Log.w(var0, var1);
            }

        }
    }
}
