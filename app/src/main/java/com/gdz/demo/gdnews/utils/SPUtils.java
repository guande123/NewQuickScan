package com.gdz.demo.gdnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class SPUtils {
    private static final String SP_START= "sp_start";
    private  static final String IS_FIRST_RUN = "first_run";
    public static boolean isFirstRun(Context context){
        SharedPreferences sp  = context.getSharedPreferences(SP_START,Context.MODE_PRIVATE);
        return     sp.getBoolean(IS_FIRST_RUN,true);
    }

    public static void setFirstStatus(Context context, boolean b) {
        SharedPreferences sp  = context.getSharedPreferences(SP_START,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(IS_FIRST_RUN,b);
        editor.commit();
    }
}
