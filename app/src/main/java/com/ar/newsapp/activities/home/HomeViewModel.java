package com.ar.newsapp.activities.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ar.newsapp.database.DataBaseHelper;
import com.ar.newsapp.database.NewsDao;
import com.ar.newsapp.network.RestClient;
import com.ar.newsapp.network.model.NewsModel;

public class HomeViewModel extends ViewModel {

    private LiveData<NewsModel> newLiveData;
    private HomeModel articlesRepo;
    private NewsDao dao;

    // Instructs Dagger 2 to provide the UserRepository parameter.
    public HomeViewModel() {
        dao = DataBaseHelper.getInstance().recipeDao();
        this.articlesRepo = new HomeModel(dao, new RestClient());
    }


    public void init() {
        if (this.newLiveData == null) {
            newLiveData = new MutableLiveData<>();
        }
        newLiveData = articlesRepo.getAllArticles();
    }

    public LiveData<NewsModel> getArticlesList() {
        return this.newLiveData;
    }

    public void doNetworkCall() {
        articlesRepo.refreshNewsList();
    }
}
