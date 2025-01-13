package com.psp.data

import com.psp.model.Alumno
import retrofit2.http.DELETE

import retrofit2.http.GET

interface ApiService {

    @GET("/alumnos")
    suspend fun getAlumnos(): List<Alumno>

    @DELETE("/alumnos/{id}")
    suspend fun deleteAlumno(id: Int)
}