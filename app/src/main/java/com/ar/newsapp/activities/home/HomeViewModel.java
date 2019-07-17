package com.ar.newsapp.activities.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ar.newsapp.components.DaggerComponentsOur;
import com.ar.newsapp.network.model.NewsArticles;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private LiveData<List<NewsArticles>> newLiveData;
    private HomeModel articlesRepo;

    @Inject
    public HomeViewModel() {
        articlesRepo = DaggerComponentsOur.create().getHomeModel();
    }


    public void init() {
        if (this.newLiveData == null) {
            newLiveData = new MutableLiveData<>();
        }
        newLiveData = articlesRepo.getAllArticles();
    }

    public LiveData<List<NewsArticles>> getArticlesList() {
        return this.newLiveData;
    }

    public void doNetworkCall() {
        articlesRepo.refreshNewsList();
    }

    public boolean isResponseNotNull(List<NewsArticles> list) {
        return list != null;
    }
}
