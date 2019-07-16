package com.ar.newsapp.network.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.ar.newsapp.database.ArticlesConverter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class NewsModel {

    @PrimaryKey(autoGenerate = false)
    private int id;

    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private int totalResults;

    @TypeConverters(ArticlesConverter.class)
    @SerializedName("articles")
    private List<NewsArticles> articlesList;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsArticles> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<NewsArticles> articlesList) {
        this.articlesList = articlesList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
