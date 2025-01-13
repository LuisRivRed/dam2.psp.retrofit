package com.psp.retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {


    fun providerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideStudentsService(): StudentsService {
        return providerRetrofit().create(StudentsService::class.java)
    }

    val studentService = provideStudentsService()

}