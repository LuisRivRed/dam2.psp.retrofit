package com.psp.data

import com.psp.model.Alumno
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiService {


    @GET("/alumnos")
    suspend fun getAlumnos(): List<Alumno>

    @GET("/alumnos/nombre/{nombre}")
    suspend fun getAlumnoNombre(@Path("nombre") nombre: String): Alumno

    @GET("/alumnos/{idAlumno}")
    suspend fun getAlumnoById(@Path("idAlumno") idAlumno: Int): Alumno

    @GET("/alumnos/curso/{curso}")
    suspend fun getAlumnoCurso(@Path("curso") curso: String): List<Alumno>

    @GET("/alumnos/asignatura/{asignatura}")
    suspend fun getAlumnoAsignatura(@Path("asignatura") asignatura: String): List<Alumno>

    @POST("/alumnos")
    suspend fun addAlumno(@Body alumno: Alumno): Alumno

    @PUT("/alumnos/{idAlumno}")
    suspend fun updateAlumno(@Path("idAlumno") idAlumno: Int, @Body alumno: Alumno): Alumno

    @PATCH("/alumnos/{idAlumno}")
    suspend fun patchAlumno(@Path("idAlumno") idAlumno: Int, @Body alumno: Alumno): Alumno

    @DELETE("/alumnos/eliminar/{idAlumno}")
    suspend fun deleteAlumno(@Path("idAlumno") idAlumno: Int): Unit
}
