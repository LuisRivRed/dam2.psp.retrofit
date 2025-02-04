package com.psp.data.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val gson = GsonBuilder().create()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor) // Aquí usamos el objeto directamente
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.84.46:8080")
        .client(okHttpClient)  // Se aplica el interceptor
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun provideApi(): ApiService = retrofit.create(ApiService::class.java)

    fun setToken(token: String) {
        AuthInterceptor.setToken(token) // Actualizamos el token en el interceptor singleton
    }
}