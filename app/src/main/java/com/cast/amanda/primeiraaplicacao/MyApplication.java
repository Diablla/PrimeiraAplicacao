package com.cast.amanda.primeiraaplicacao;

import android.app.Application;

import com.cast.amanda.primeiraaplicacao.util.AppUtil;

/**
 * Created by Amanda on 23/07/2015.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        AppUtil.CONTEXT = getApplicationContext();
        super.onCreate();
    }
}
