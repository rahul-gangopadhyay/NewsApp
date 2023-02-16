package com.example.news_app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines?country=in&apiKey=4b02ce712d974d99ac39bcab1a4cca52")
    fun getNewsList(): Call<NewsResponse>
}