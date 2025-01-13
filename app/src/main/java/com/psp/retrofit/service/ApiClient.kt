package com.psp.retrofit.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL="http://10.0.2.2:8080/"

    fun providerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    fun provideApiService(): ApiService {
        return providerRetrofit().create(ApiService::class.java)
    }
}