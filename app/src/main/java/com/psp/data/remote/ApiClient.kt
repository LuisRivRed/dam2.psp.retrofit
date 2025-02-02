package com.psp.data.remote

import com.psp.domain.AlumnosApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {

    private const val BASE_URL = "http://10.0.2.2:1111/"


    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideAlumnosApi(): AlumnosApi {
        return provideRetrofit().create(AlumnosApi::class.java)
    }
}