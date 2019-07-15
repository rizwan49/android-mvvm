package com.ar.newsapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ar.newsapp.network.model.NewsModel;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM newsmodel")
    LiveData<NewsModel> getAll();

    @Insert(onConflict = REPLACE)
    void insertAll(NewsModel... newsModels);

    @Delete
    void delete(NewsModel newsModel);

    @Insert(onConflict = REPLACE)
    void insertList(NewsModel newsModel);

}
