package com.psp.domain

import com.psp.domain.model.Alumno
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AlumnosApi {

    //Está hecho en base a la API que hice en el anterior ejercicio (por el problema del postman, aunque aqui no sea un problema)

    @GET("/alumnos")
    suspend fun getAlumnos(): List<Alumno>

    @GET("/alumnos/{curso}")
    suspend fun getAlumnosByCurso(@Path("curso") curso: String): List<Alumno>

    @POST("/alumnos")
    suspend fun createAlumno(@Body alumno: Alumno): Alumno

    @GET("/alumnos/{nombre}")
    suspend fun getAlumnoByNombre(@Path("nombre") nombre: String): Alumno?

    @GET("/alumnos/eliminar/{id}")
    suspend fun deleteAlumnoWithGet(@Path("id") id: Int): String

    @DELETE("/alumnos/{id}")
    suspend fun deleteAlumno(@Path("id") id: Int)


}