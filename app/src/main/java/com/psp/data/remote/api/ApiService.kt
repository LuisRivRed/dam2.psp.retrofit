package com.psp.data.remote.api

import com.psp.model.Alumno
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {


    @GET("alumnos")
    suspend fun fetchAlumnos(): List<Alumno>

    @GET("alumnos/curso/{curso}")
    suspend fun fetchAlumnosByCurso(@Path("curso") curso: String): List<Alumno>

    @GET("alumnos/nombre/{nombre}")
    suspend fun fetchAlumnosByName(@Path("nombre") name: String): Alumno

    @POST("alumnos")
    suspend fun addAlumno(@Body alumno: Alumno)

    @DELETE("alumnos/eliminar/{idAlumno}")
    suspend fun deleteAlumnoById(@Path("idAlumno") id: Int)
}