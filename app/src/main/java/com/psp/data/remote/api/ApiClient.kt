@file:JvmName("ApiServiceKt")

package com.psp.data.remote.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://192.168.56.1:8080/" //IP de localhost (si no hay problemas con el emulador)
class ApiClient {

    // Para que Gson sea capaz de parsear los objetos de la API
    val gson = GsonBuilder()
        .setLenient()
        .create()


    // Create a logging interceptor
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Create an OkHttpClient and add the logging interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}