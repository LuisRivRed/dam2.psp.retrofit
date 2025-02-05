package com.psp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val authInterceptor = AuthInterceptor()

    private const val BASE_URL_API = "http://10.0.2.2:8080/"
    fun provideRetrofit(): Retrofit {
     return Retrofit.Builder()
         .baseUrl(BASE_URL_API)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
    }

    fun setToken(token: String) {
        authInterceptor.setToken(token)
    }
}