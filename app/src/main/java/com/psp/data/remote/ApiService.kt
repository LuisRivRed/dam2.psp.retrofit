package com.psp.data.remote

import com.psp.data.model.Alumno
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/alumnos")
    suspend fun getAlumnos(): Response<List<Alumno>>

    @GET("/alumnos/nombre/{nombre}")
    suspend fun getAlumnosByName(@Path("nombre") nombre: String): Response<List<Alumno>>

}