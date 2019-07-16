package com.ar.newsapp.components;

import com.ar.newsapp.activities.home.HomeActivity;
import com.ar.newsapp.activities.home.HomeModel;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;


@Module
public interface ComponentsOur {
    void inject(HomeActivity mainActivity);
}
