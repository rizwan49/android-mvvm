package com.ar.newsapp.activities.detail;

import android.arch.lifecycle.ViewModel;

import com.ar.newsapp.network.model.NewsArticles;


public class DetailViewModel extends ViewModel {

    private NewsArticles articlesLiveData;

    public DetailViewModel() {

    }

    public void init() {

    }

    public NewsArticles getSelectedArticlesInfo() {
        return this.articlesLiveData;
    }


    public void init(NewsArticles selectedNewsArticle) {
        articlesLiveData = selectedNewsArticle;
    }
}
