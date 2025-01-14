package com.psp.data

import com.psp.model.Alumno
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/alumnos")
    suspend fun getAlumnos(): List<Alumno>

    @GET("/alumnos/{id}")
    suspend fun getAlumno(@Path("id") id: Int): Alumno
}