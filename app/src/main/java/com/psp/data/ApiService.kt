package com.psp.data

import com.psp.model.LoginRequest
import com.psp.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @GET("alumnos")
    suspend fun getAlumnos(@Header("Authorization") token: String): Response<List<AlumnoApiModel>>
}