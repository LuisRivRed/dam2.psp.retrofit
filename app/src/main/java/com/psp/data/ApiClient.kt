package com.psp.data

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL_API = "http://192.168.18.106:8080/"

    private val authInterceptor = AuthInterceptor()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_API)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val alumnoService: AlumnoService = retrofit.create(AlumnoService::class.java)

    fun setToken(token: String) {
        authInterceptor.setToken(token)
    }

    fun getToken( ): String{
        return authInterceptor.getToken()
    }
}