package com.ar.newsapp.components;

import com.ar.newsapp.activities.home.HomeModel;
import com.ar.newsapp.database.AppDatabase;
import com.ar.newsapp.database.NewsDao;

import dagger.Component;


@Component(modules = {AppDatabase.class, NewsDao.class})
public interface ComponentsOur {
    HomeModel getHomeModel();
}
