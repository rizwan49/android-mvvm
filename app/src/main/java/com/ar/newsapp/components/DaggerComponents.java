package com.ar.newsapp.components;

import com.ar.newsapp.activities.home.HomeActivity;
import com.ar.newsapp.activities.home.HomeModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = HomeModel.class)
public interface DaggerComponents {
    void inject(HomeActivity mainActivity);
}
