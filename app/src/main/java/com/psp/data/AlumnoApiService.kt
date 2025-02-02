package com.psp.data

import com.psp.model.Alumno
import retrofit2.Response
import retrofit2.http.*

interface AlumnoApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @GET("/alumnos")
    suspend fun getAlumnos(): Response<List<Alumno>>

    @GET("/alumnos/curso/{curso}")
    suspend fun getAlumnosByCurso(@Path("curso") curso: String): List<Alumno>

    @GET("/alumnos/nombre/{nombre}")
    suspend fun getAlumnoByNombre(@Path("nombre") nombre: String): Alumno

    @POST("/alumnos")
    suspend fun saveAlumno(@Body alumno: Alumno): Alumno?

    @DELETE("/alumnos/eliminar/{idAlumno}")
    suspend fun deleteAlumno(@Path("idAlumno") idAlumno: Int)

}