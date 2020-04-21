package com.zzz.javatemplate.manager;

import com.zzz.javatemplate.BuildConfig;
import com.zzz.javatemplate.manager.api.JuHeService;
import com.zzz.javatemplate.model.ConstellationResultModel;
import com.zzz.javatemplate.model.NewsResultModel;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static OkHttpClient okHttpClient;
    private JuHeService juHeService;
    private static RetrofitHelper newsInstance;
    private static RetrofitHelper collInstance;

    public RetrofitHelper(UrlType type) {
        String url = "";
        switch (type) {
            case NEWS:
                url = BuildConfig.JUHE_URL_NEWS;
                break;
            case CONSTELLATION:
                url = BuildConfig.JUHE_URL_COLL;
                break;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        juHeService = retrofit.create(JuHeService.class);
    }

    public Call<NewsResultModel> getNews() {
        return juHeService.getNews();
    }

    public Call<ConstellationResultModel> getConstellation(String type, String name) {
        return juHeService.getConstellation(name, type, BuildConfig.JUHE_CONSTELLATION_APPKEY);
    }

    public static RetrofitHelper getInstance(UrlType type) {
        switch (type) {
            case NEWS:
                if (newsInstance == null)
                    newsInstance = new RetrofitHelper(type);
                return newsInstance;

            case CONSTELLATION:
                if (collInstance == null)
                    collInstance = new RetrofitHelper(type);
                return collInstance;
            default:
                return newsInstance;
        }

    }

    public static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .addInterceptor(logInterceptor)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }

    public enum UrlType {
        NEWS,
        CONSTELLATION
    }

}
