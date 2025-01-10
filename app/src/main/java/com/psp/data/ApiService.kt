package com.psp.data

import com.psp.domain.model.Alumno
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/alumnos")
    suspend fun getAlumnos(): Response<List<Alumno>>
}