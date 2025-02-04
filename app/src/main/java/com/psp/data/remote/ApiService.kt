package com.psp.data.remote

import com.psp.data.model.Alumno
import com.psp.data.model.LoginRequest
import com.psp.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("alumnos/{id}")
    suspend fun getAlumno(@Path("id") id: Int): Response<Alumno>

    @DELETE("alumnos/{id}")
    suspend fun deleteAlumno(@Path("id") id: Int): Response<Unit>

    @POST("alumnos")suspend fun addAlumno(@Body alumno: Alumno): Response<Alumno>

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @GET("alumnos")
    suspend fun getAlumnos(@Header("Authorization") token: String):
            Response<List<Alumno>>
}
