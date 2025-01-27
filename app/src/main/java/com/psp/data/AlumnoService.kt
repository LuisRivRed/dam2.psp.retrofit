package com.psp.data

import com.psp.model.Alumno
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AlumnoService {

    @GET("/alumnos")
    suspend fun getAlumnos(): List<Alumno>

    @GET("/alumnos/nombre/{nombre}")
    suspend fun getAlumnoNombre(@Path("nombre") nombre: String): Alumno

    @GET("/alumnos/id/{id}")
    suspend fun getAlumnoId(@Path("id") id: Int): Alumno

    @GET("/alumnos/curso/{curso}")
    suspend fun getAlumnoCurso(@Path("curso") curso: String): List<Alumno>

    @POST("/alumnos")
    suspend fun addAlumno(@Body alumno: Alumno): Alumno

    @DELETE("/alumnos/eliminar/{idAlumno}")
    suspend fun deleteAlumno(@Path("idAlumno") id: Int): Boolean

}