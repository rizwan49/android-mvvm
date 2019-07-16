package com.ar.newsapp;

import android.app.Application;
import android.content.Context;

import com.ar.newsapp.components.ComponentsOur;
import com.facebook.stetho.Stetho;

import dagger.android.DaggerActivity;

public class AppApplication extends Application {
    public static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Stetho.initializeWithDefaults(this);
    }
}
