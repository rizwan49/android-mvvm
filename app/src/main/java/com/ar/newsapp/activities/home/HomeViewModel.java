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
//    private NewsDao dao;

    // Instructs Dagger 2 to provide the UserRepository parameter.
    @Inject
    public HomeViewModel() {
        articlesRepo = DaggerComponentsOur.create().getHomeModel();

//        dao = DataBaseHelper.getInstance().newsDao();
//        this.articlesRepo = new HomeModel(dao, new RestClient());
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
}
