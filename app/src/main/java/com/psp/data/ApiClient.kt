package com.psp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType


object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:8080/"
    private val authInterceptor = AuthInterceptor()
    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(authInterceptor)
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()
    val apiService: ApiService = retrofit.create(ApiService::class.java)
    fun setToken(token: String) {
        authInterceptor.setToken(token)
    }
}
