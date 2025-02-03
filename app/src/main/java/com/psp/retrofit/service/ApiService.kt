package com.psp.retrofit.service

import com.psp.model.Alumno
import com.psp.model.LoginRequest
import com.psp.model.TokenResponse
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
    suspend fun getAlumnos(@Header("Authorization") token: String): Response<List<Alumno>>
    @GET("alumno/{id}")
    suspend fun getAlumnoById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Alumno>

    @GET("alumno/{curso}")
    suspend fun getAlumnosByCurso(
        @Header("Authorization") token: String,
        @Path("curso") curso: String
    ): Response<List<Alumno>>

}