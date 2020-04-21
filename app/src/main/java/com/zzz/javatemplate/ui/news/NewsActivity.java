package com.zzz.javatemplate.ui.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.zzz.javatemplate.R;
import com.zzz.javatemplate.manager.RetrofitHelper;
import com.zzz.javatemplate.model.NewsResultModel;
import com.zzz.javatemplate.ui.web.WebActivity;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    RecyclerView rvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        rvNews = findViewById(R.id.rv_news);
        initView();
        initData();

    }

    private void initView() {
        setUpRv();
    }

    private void initData() {
        refreshNews();
    }

    private void setUpRv() {
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvNews.setAdapter(new NewsAdapter(NewsActivity.this,new ArrayList<NewsResultModel.News>()));
        ((NewsAdapter)rvNews.getAdapter()).setOnCellClickListener(new NewsAdapter.OnCellClickListener() {
            @Override
            public void onCellClick(NewsResultModel.News news) {
                jumpToWebDetailActivity(news.getUrl());
            }
        });
    }

    private void refreshNews() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RetrofitHelper.getInstance(RetrofitHelper.UrlType.NEWS)
                        .getNews()
                        .enqueue(new Callback<NewsResultModel>() {
                            @Override
                            public void onResponse(Call<NewsResultModel> call, final Response<NewsResultModel> response) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((NewsAdapter) rvNews.getAdapter()).replaceData(response.body().getResult().getData());
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<NewsResultModel> call, final Throwable t) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(NewsActivity.this, "请求失败" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
            }
        });


    }

    private void jumpToWebDetailActivity(String detailUrl){
        Intent intent = new Intent(NewsActivity.this, WebActivity.class);
        intent.putExtra("url",detailUrl);
        startActivity(intent);
    }
}
