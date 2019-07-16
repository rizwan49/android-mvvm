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

    private static final String TAG = "HomeModel";
    private final NewsDao newsDao;
    private final RestClient restClient;
    private int page = 0;
    private int totalResult;
    private int receivedCount;
    private boolean isLoadCompleted;

    public HomeModel(NewsDao newsDao, RestClient restClient) {
        this.newsDao = newsDao;
        this.restClient = restClient;
        this.isLoadCompleted = false;
        page = 0;
        totalResult = 0;
        receivedCount = 0;
    }

    public LiveData<NewsModel> getAllArticles() {
        refreshNewsList();
        // Returns a LiveData object directly from the database.

        return newsDao.getAll();
    }

    public void refreshNewsList() {

        Log.d(TAG, "before pageCount:" + page + " totalCount:" + totalResult + " TotalReceivedCount:" + receivedCount + " isLoadCompleted:" + isLoadCompleted);
        if (isLoadCompleted)
            return;

        restClient.getApiService().getAllArticles(++page).enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful()) {

                    totalResult = response.body().getTotalResults();
                    receivedCount = receivedCount + response.body().getArticlesList().size();
                    if (receivedCount >= totalResult) {
                        isLoadCompleted = true;
                    }
                    new Thread(() -> newsDao.insertList(response.body())).start();
                    Log.d(TAG, "after pageCount:" + page + " totalCount:" + totalResult + " TotalReceivedCount:" + receivedCount + " isLoadCompleted:" + isLoadCompleted);

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
