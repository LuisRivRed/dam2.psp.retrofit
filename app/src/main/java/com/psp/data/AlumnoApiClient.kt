package com.psp.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AlumnoApiClient {

    private const val BASE_URL = "http://10.0.2.2:8080"
    private val gson = GsonBuilder().create()

    private val authInterceptor = AuthInterceptor()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val apiService = retrofit.create(AlumnoApiService::class.java)

    fun setToken(token: String) {
        authInterceptor.setToken(token)
    }
}