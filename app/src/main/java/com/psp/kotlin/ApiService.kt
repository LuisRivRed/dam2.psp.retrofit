package com.psp.kotlin

import com.psp.model.Alumno
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/alumnos")
    suspend fun getAlumnos(): Response<List<Alumno>>

    @GET("/alumnos/curso/{curso}")
    suspend fun getAlumnosByCurso(@Path("curso") curso: String): Response<List<Alumno>>

    @GET("/alumnos/alumno/{nombre}")
    suspend fun getAlumnoByNombre(@Path("nombre") nombre: String): Response<List<Alumno>>

    @POST("/alumnos")
    suspend fun addAlumno(@Body alumno: Alumno): Response<Alumno>

    @DELETE("/alumnos/eliminar/{idAlumno}")
    suspend fun deleteAlumno(@Path("idAlumno") idAlumno: Int): Response<Void>
}