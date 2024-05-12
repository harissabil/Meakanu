package com.harissabil.meakanu.data.remote.api.news

import com.harissabil.meakanu.data.remote.response.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceNews {
    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String = "plant AND crop",
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsResponse
}