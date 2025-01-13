package com.psp.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL_API = "http://127.0.0.1/alumno"

interface AlumnoService {

    @GET("alumnos")
    suspend fun getAlumnos(): Response<List<AlumnoApiModel>>

    @GET("alumnos/curso/{curso}")
    suspend fun getAlumno(@Path("curso") id: String): Response<List<AlumnoApiModel>>

    @GET("alumnos/nombre/{nombre}")
    suspend fun getAlumnoPorNombre(@Path("nombre") nombre: String): Response<List<AlumnoApiModel>>

    @POST("alumnos")
    suspend fun addAlumno(@Body alumno: AlumnoApiModel): Response<AlumnoApiModel>

    @DELETE("alumnos/{id}")
    suspend fun deleteAlumno(@Path("id") id: String): Response<Unit>
}