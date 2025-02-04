package com.psp.data.remote

import com.psp.data.model.Alumno
import com.psp.data.model.LoginRequest
import com.psp.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @GET("alumnos")
    suspend fun getAlumnos(): Response<List<Alumno>>

    @GET("/alumnos/{nombre}")
    suspend fun getAlumnosByName(@Path("nombre") nombre: String): Response<Alumno>

    @GET("/alumnos/curso/{curso}")
    suspend fun getAlumnosByCurso(@Path("curso") curso: String): Response<List<Alumno>>

    @GET("/alumnos/id/{id}")
    suspend fun getAlumnoById(@Path("id") id: Int): Response<Alumno>

    @POST("/alumnos")
    suspend fun addAlumno(@Body alumno: Alumno): Response<Alumno>

    @DELETE("/alumnos/{id}")
    suspend fun deleteAlumno(@Path("id") id: Int): Response<Unit>
}