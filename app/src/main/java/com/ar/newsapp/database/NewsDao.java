package com.ar.newsapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ar.newsapp.network.model.NewsArticles;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM newsarticles")
    LiveData<List<NewsArticles>> getAll();

    @Insert(onConflict = REPLACE)
    void insertAll(NewsArticles... newsArticles);

    @Delete
    void delete(List<NewsArticles> newsArticles);

    @Insert(onConflict = REPLACE)
    void insertList(List<NewsArticles> newsArticles);

}
