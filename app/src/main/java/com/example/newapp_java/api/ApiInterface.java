package com.example.newapp_java.api;

import com.example.newapp_java.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<News>  getNews(
            @Query("country") String country ,

            @Query("category") String category ,

            @Query("apiKey") String apiKey
    ) ;
}
