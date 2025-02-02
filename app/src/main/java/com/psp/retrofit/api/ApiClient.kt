package com.psp.retrofit.api

import AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {


    val token =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJTdHVkZW50cyIsImlzcyI6ImF1dGhTdHVkZW50cyIsInVzZXJuYW1lIjoiYWRtaW4ifQ.Ag7V8ywg_zRD2n3bWB5Dn2ReoangyT-iJDCKg2VT79U"

    fun providerRetrofit(token: String): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(token))
            .build()

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideStudentsService(): StudentsService {
        return providerRetrofit(token).create(StudentsService::class.java)
    }

    val studentService = provideStudentsService()

}