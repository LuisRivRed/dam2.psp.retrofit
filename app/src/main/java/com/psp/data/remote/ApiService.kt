package com.psp.data.remote

import com.psp.data.model.Alumno
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("alumnos")
    suspend fun getAlumnos(): Response<List<Alumno>>

    @GET("alumnos/{id}")
    suspend fun getAlumno(@Path("id") id: Int): Response<Alumno>

    @DELETE("alumnos/eliminar/{id}")
    suspend fun deleteAlumno(@Path("id") id: Int): Response<Unit>
}