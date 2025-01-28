package com.psp.retrofit.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {


    //private const val BASE_URL_API = "http://192.168.32.1:8080/" // casa cable

    //private var baseUrl: String = "http://192.168.18.112:8080/" // clase clable

    //private var baseUrl: String = "http://10.0.2.2:8099/" // clase clable
    private var baseUrl: String = "http://127.0.0.1:51699/" // clase clable

    fun setBaseUrlFromPreferences(prefUrl: String) {
        baseUrl = prefUrl
    }

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
