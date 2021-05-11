package com.beehive.beerrate.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BeerServiceGenerator {
    private const val BASE_URL = "https://simonmdsn.com/api/"

    // Beer API credentials. Using the basic access authentication method.
    private const val CREDENTIALS = "YmVlaGl2ZTphbmRyb2lk"
    private val builder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
    private var retrofit = builder.build()
    private val httpClient = OkHttpClient.Builder()
    fun <S> createService(serviceClass: Class<S>?): S {
        httpClient.interceptors().clear()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val builder =
                original.newBuilder().header("Authorization", "Basic $CREDENTIALS")
            val request = builder.build()
            chain.proceed(request)
        }
        builder.client(httpClient.build())
        retrofit = builder.build()
        return retrofit.create(serviceClass!!);
    }
}