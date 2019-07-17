package com.ar.newsapp.activities.detail;

import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import com.ar.newsapp.network.model.NewsArticles;


public class DetailViewModel extends ViewModel {

    private NewsArticles articlesLiveData;

    public DetailViewModel() {

    }

    public NewsArticles getSelectedArticlesInfo() {
        return this.articlesLiveData;
    }


    public void init(NewsArticles selectedNewsArticle) {
        articlesLiveData = selectedNewsArticle;
    }

    public boolean isSharedObjectNull() {
        return articlesLiveData == null;
    }

    public boolean isStringEmpty(String value) {
        return TextUtils.isEmpty(value);
    }
}
