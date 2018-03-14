package com.zhang.wanandroiod;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
/**
 * Created by Administrator on 2018/2/27
 */

public class MyApplication extends Application {
    private static  MyApplication mMyApplication;

    @Override
    public void onCreate() {
        mMyApplication = this;
        Fresco.initialize(this);
        super.onCreate();
    }

    public static Application getApplictaion() {
        return mMyApplication;
    }
}
