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

    @GET("alumnos/alumno/{id}")
    suspend fun requestAlumno(@Path("id") id: Int): Response<Alumno>

    @POST("alumnos")
    suspend fun createAlumno(@Body alumno: Alumno): Response<Alumno>

    @GET("alumnos/eliminar/{id}")
    suspend fun deleteAlumno(@Path("id") id: Int): Response<Void>
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>
    @GET("alumnos")
    suspend fun getAlumnos(@Header("Authorization") token: String): Response<List<Alumno>>

}