package com.ar.newsapp.network;

import com.ar.newsapp.network.model.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    String KEY = "c923c02f83e84a1c802a3060931c518e";
    String BASE_URL = "https://newsapi.org";
    String ARTICLES_URL = "/v2/top-headlines?country=in&category=business&apiKey=" + KEY;

    @GET(ARTICLES_URL)
    Call<NewsModel> getAllArticles(@Query("page") int pageIndex);
}
