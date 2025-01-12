package com.psp.data

import com.psp.domain.model.Alumno
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/alumnos")
    suspend fun getAlumnos(): List<Alumno>

    @GET("/alumnos/nombre/{nombre}")
    suspend fun getAlumnoNombre(@Path("nombre") nombre: String): Alumno


    @GET("/alumnos/curso/{curso}")
    suspend fun getAlumnoCurso(@Path("curso") curso: String): List<Alumno>

    @POST("/alumnos")
    suspend fun addAlumno(@Body alumno: Alumno): Alumno

    @DELETE("/alumnos/eliminar/{idAlumno}")
    suspend fun deleteAlumno(@Path("idAlumno") idAlumno: Int): Unit
}