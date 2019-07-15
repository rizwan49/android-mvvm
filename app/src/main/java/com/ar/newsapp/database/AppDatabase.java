package com.ar.newsapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.ar.newsapp.network.model.NewsModel;

@Database(entities = {NewsModel.class}, version = 1, exportSchema = false)
@TypeConverters({ArticlesConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao recipeDao();
}