package com.ar.newsapp.database;

import android.arch.persistence.room.TypeConverter;

import com.ar.newsapp.network.model.NewsArticles;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ArticlesConverter {

    @TypeConverter
    public String fromList(List<NewsArticles> articles) {
        if (articles == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<NewsArticles>>() {
        }.getType();
        return gson.toJson(articles, type);
    }

    @TypeConverter
    public List<NewsArticles> toList(String itemString) {
        if (itemString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<NewsArticles>>() {
        }.getType();
        return gson.fromJson(itemString, type);
    }

}
