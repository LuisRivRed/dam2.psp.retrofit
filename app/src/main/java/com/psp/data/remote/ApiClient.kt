package com.psp.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.psp.domain.AlumnosApi
import com.psp.retrofit.AuthInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit


object ApiClient {

    private const val BASE_URL = "http://10.0.2.2:1111/"

    private val authInterceptor = AuthInterceptor()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    private fun provideRetrofit(): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .build()
    }

    fun provideAlumnosApi(): AlumnosApi {
        return provideRetrofit().create(AlumnosApi::class.java)
    }

    fun setToken(token: String) {
        authInterceptor.setToken(token)
    }
}