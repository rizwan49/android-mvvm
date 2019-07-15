package com.ar.newsapp.activities.home;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.ar.newsapp.database.NewsDao;
import com.ar.newsapp.network.RestClient;
import com.ar.newsapp.network.model.NewsModel;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class HomeModel {

    private final NewsDao newsDao;
    private final RestClient restClient;

    public HomeModel(NewsDao newsDao, RestClient restClient) {
        this.newsDao = newsDao;
        this.restClient = restClient;
    }

    public LiveData<NewsModel> getAllRecipes() {
        refreshRecipe();
        // Returns a LiveData object directly from the database.

        return newsDao.getAll();
    }

    private void refreshRecipe() {

        restClient.getApiService().getAllRecipes().enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful()) {
                    new Thread(() -> newsDao.insertList(response.body())).start();
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.e("Error","failed"+t.getMessage());
            }
        });
        // Check for errors here.

    }
}
