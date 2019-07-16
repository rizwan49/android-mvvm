package com.ar.newsapp.activities.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.ar.newsapp.R;
import com.ar.newsapp.SimpleIdlingResource;
import com.ar.newsapp.Utils;
import com.ar.newsapp.activities.detail.DetailActivity;
import com.ar.newsapp.adapters.NewsHeadlinesAdapter;
import com.ar.newsapp.network.model.NewsArticles;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NewsHeadlinesAdapter.ListItemOnClickListener {

    @Nullable
    public static SimpleIdlingResource mIdlingResource;
    HomeViewModel viewModel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    NewsHeadlinesAdapter adapter;
    List<NewsArticles> list;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    RecyclerView.LayoutManager layoutManager;
    private String TAG = HomeActivity.class.getName();

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public static IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            synchronized (HomeActivity.class) {
                if (mIdlingResource == null) {
                    mIdlingResource = new SimpleIdlingResource();
                }
            }
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        getIdlingResource();
        mIdlingResource.setIdleState(false);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        Utils.showViews(progressBar);
        viewModel.init();
        setupToolbar();
        bindAdapter();
        setupObserver();
    }

    private void setupToolbar() {
        mToolbar.setTitle(R.string.headlines);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void setupObserver() {
        viewModel.getArticlesList().observe(this, newsResponse -> {
            if (newsResponse != null) {
                adapter.addAllItem(newsResponse.getArticlesList());
                Utils.hideViews(progressBar);
                mIdlingResource.setIdleState(true);
            }
        });
    }

    private void bindAdapter() {
        adapter = new NewsHeadlinesAdapter(list, this) {
            @Override
            public void loadMore() {
                Utils.showViews(progressBar);
                viewModel.doNetworkCall();
            }
        };
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = new GridLayoutManager(this, 2);
            // In landscape
        } else {
            // In portrait
            layoutManager = new GridLayoutManager(this, 1);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(NewsArticles selectedObject, View view) {
        Log.d(TAG, "clicked :" + selectedObject.getTitle());
        DetailActivity.initiateDetailActivity(this,selectedObject,view);
    }
}
