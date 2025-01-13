package com.psp.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlumnoApiClient() {
    private val gson = GsonBuilder().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://127.0.0.1:8080")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val apiService = retrofit.create(AlumnoApiService::class.java)
}
//http://192.168.18.166:8080