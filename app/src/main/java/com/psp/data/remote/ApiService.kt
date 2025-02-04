package com.psp.data.remote

import com.psp.data.model.Alumno
import com.psp.data.model.LoginRequest
import com.psp.data.model.TokenResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("alumnos")
    suspend fun getAlumnos(): Response<List<Alumno>>

    @GET("alumnos/{id}")
    suspend fun getAlumno(@Path("id") id: Int): Response<Alumno>

    @DELETE("alumnos/eliminar/{id}")
    suspend fun deleteAlumno(@Path("id") id: Int): Response<Unit>

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>
}
