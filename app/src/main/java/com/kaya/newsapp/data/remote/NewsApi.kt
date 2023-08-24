package com.kaya.newsapp.data.remote

import com.kaya.newsapp.data.local.NewsResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("v2/top-headlines?country=us&sortBy=popularity")
    suspend fun getNews(
        @Query("apikey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything?q=sport&sortBy=popularity")
    suspend fun getSportsNews(
        @Query("apikey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything?q=health&sortBy=popularity")
    suspend fun getHealthNews(
        @Query("apikey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything?q=finance&sortBy=popularity")
    suspend fun getFinanceNews(
        @Query("apikey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything?sortBy=popularity")
    suspend fun searchForNews(
        @Query("q") query: String,
    )

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY =  "f1ea2f9a825443c4bbd7877edd3d463f"
    }
}