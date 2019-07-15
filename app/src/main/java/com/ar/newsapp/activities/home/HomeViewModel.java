package com.ar.newsapp.activities.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ar.newsapp.database.DataBaseHelper;
import com.ar.newsapp.database.NewsDao;
import com.ar.newsapp.network.RestClient;
import com.ar.newsapp.network.model.NewsModel;

import javax.inject.Singleton;

@Singleton
public class HomeViewModel extends ViewModel {

    private LiveData<NewsModel> newLiveData;
    private HomeModel recipeRepo;
    private NewsDao dao;

    // Instructs Dagger 2 to provide the UserRepository parameter.
    public HomeViewModel() {
        dao = DataBaseHelper.getInstance().recipeDao();
        this.recipeRepo = new HomeModel(dao, new RestClient());
    }


    public void init() {
        if (this.newLiveData != null) {
            // ViewModel is created on a per-Fragment basis, so the userId
            // doesn't change.
            return;
        }
        newLiveData = recipeRepo.getAllRecipes();
    }

    public LiveData<NewsModel> getRecipesList() {
        return this.newLiveData;
    }

}
