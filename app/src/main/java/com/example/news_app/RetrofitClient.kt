package com.example.news_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        private const val BASE_URL = "https://newsapi.org/v2/"
        private var retrofit: Retrofit ?= null
        fun getRetrofitClient(): Retrofit{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}