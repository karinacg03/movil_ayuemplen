package com.adso.myapplicationsantes.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofitclient {
    private val BASE_URL = "http://10.0.2.2:8000/api/"
    /*private val loggingInterceptor=HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }*/
    /*private val client= OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()*/
    val apiservice:Apiservice by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiservice::class.java)
    }
}