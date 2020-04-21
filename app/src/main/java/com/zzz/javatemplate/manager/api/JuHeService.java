package com.zzz.javatemplate.manager.api;

import com.zzz.javatemplate.BuildConfig;
import com.zzz.javatemplate.model.ConstellationResultModel;
import com.zzz.javatemplate.model.NewsResultModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JuHeService {

    //获取星座运势
    @GET("constellation/getAll")
    Call<ConstellationResultModel> getConstellation(
            @Query("consName")String name,
            @Query("type")String type,
            @Query("key")String key
    );

    //获取资讯
    @GET("toutiao/index?type=top&key="+ BuildConfig.JUHE_NEWS_APPKEY)
    Call<NewsResultModel> getNews();

}