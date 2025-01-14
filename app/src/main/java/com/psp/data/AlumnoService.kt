package com.psp.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AlumnoService {

    @GET("alumnos")
    suspend fun getAlumnos(): Response<List<AlumnoApiModel>>

    @GET("alumnos/curso/{curso}")
    suspend fun getAlumnosByCurso(@Path("curso") curso: String): Response<List<AlumnoApiModel>>

    @GET("alumnos/nombre/{paramNombre}")
    suspend fun getAlumnoByNombre(@Path("paramNombre") nombre: String): Response<AlumnoApiModel?>

    @POST("alumnos")
    suspend fun addAlumno(@Body alumno: AlumnoApiModel): Response<AlumnoApiModel>

    @DELETE("alumnos/eliminar/{idAlumno}")
    suspend fun eliminarAlumnoDelete(@Path("idAlumno") idAlumno: Int): Response<String>

}