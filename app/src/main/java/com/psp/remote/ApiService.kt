package com.psp.remote

import com.api.model.Aula
import com.psp.model.LoginRequest
import com.psp.model.TokenResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("aula/{id}")
    suspend fun getAulasbyid(@Path("id") id: Int): Response<Aula>

    @DELETE("aula/{id}")
    suspend fun deleteAula(@Path("id") id: Int): Response<Unit>

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @GET("aula")
    suspend fun getAulas(@Header("Authorization") token: String):
            Response<List<Aula>>
}