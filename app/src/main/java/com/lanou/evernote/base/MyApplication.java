package com.lanou.evernote.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by zouguohua on 16/7/18.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }

    public static Context getContext() {
        return context;
    }
}
