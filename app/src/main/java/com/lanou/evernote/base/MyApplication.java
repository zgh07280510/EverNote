package com.lanou.evernote.base;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * Created by zouguohua on 16/7/18.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        Bmob.initialize(this, "3e8e0dce858ecb9845dcf7eceb687563");
    }

    public static Context getContext() {
        return context;
    }
}
