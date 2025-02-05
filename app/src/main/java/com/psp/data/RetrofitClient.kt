package com.psp.data

import com.psp.retrofit.AuthInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitClient {

    private const val BASE_URL = "http://localhost:8080/"
    private val authInterceptor = AuthInterceptor()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        //.addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val alumnoService: AlumnoService = retrofit.create(AlumnoService::class.java)

    fun setToken(token: String){
        authInterceptor.setToken(token)
    }

    //El asConverterFactory me da fallo
}