package com.ar.newsapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.ar.newsapp.network.model.NewsArticles;

@Database(entities = {NewsArticles.class}, version = 1, exportSchema = false)
@TypeConverters({SourceConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}