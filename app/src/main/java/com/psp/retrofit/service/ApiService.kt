package com.psp.retrofit.service

import com.psp.model.Alumno
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("aulas")
    suspend fun requestAlumnos(): Response<List<Alumno>>
}