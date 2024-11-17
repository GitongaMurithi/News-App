package com.example.newsapp.data.remote

import com.example.newsapp.data.dto.NewsDto
import com.example.newsapp.util.Commons
import retrofit2.http.GET
import retrofit2.http.Query

// https://newsapi.org/v2/top-headlines?country=us&apiKey=b3d55c9d59a74bedab0b32257201d00b

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country : String = "us",
        @Query("category") category : String,
        @Query("apiKey") apiKey : String = Commons.API_KEY
    ) : NewsDto

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query : String,
        @Query("apiKey") apiKey : String = Commons.API_KEY
    ) : NewsDto

}
