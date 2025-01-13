package com.psp.data

import edu.iesam.pspretrofit.data.remote.AlumnoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://192.168.1.44:88/"

    val retrofit: AlumnoService by lazy {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(AlumnoService::class.java)
    }
}