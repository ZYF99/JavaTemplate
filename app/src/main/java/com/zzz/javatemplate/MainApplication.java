package com.zzz.javatemplate;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }
}
