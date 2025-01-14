package com.psp.data.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val gson = GsonBuilder().create()

    fun provideApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.18.114:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}