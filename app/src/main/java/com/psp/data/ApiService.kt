package com.psp.data

import com.psp.model.Alumno
import com.psp.model.Curso
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //Devuelve una lista de alumnos
    @GET("alumnos")
    suspend fun getAlumnos(): Response<List<Alumno>>

    //Devuelve un alumno por su id
    @GET("alumnos/id/{id}")
    suspend fun getAlumnoById(@Path("id") id: Int): Response<Alumno>

    //Devuelve un alumno por su nombre
    @GET("alumnos/nombre/{nombre}")
    suspend fun getAlumnoByNombre(@Path("nombre") nombre: String): Response<Alumno>

    //Devuelve alumnos por su curso
    @GET("alumnos/curso/{curso}")
    suspend fun getAlumnosByCurso(@Path("curso") curso: Curso): Response<List<Alumno>>

    @POST("alumnos")
    suspend fun addAlumno(@Body alumno: Alumno): Response<Alumno>

    @DELETE("alumnos/id/{id}")
    suspend fun deleteAlumno(@Path("id") id: Int): Response<Boolean>


}