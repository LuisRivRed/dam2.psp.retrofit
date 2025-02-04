package com.psp.data

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

private const val BASE_URL_API = "http://172.20.10.4/alumno"

interface AlumnoService {

    @GET("alumnos/curso/{curso}")
    suspend fun getAlumno(@Path("curso") id: String): Response<List<AlumnoApiModel>>

    @GET("alumnos/nombre/{nombre}")
    suspend fun getAlumnoPorNombre(@Path("nombre") nombre: String): Response<List<AlumnoApiModel>>

    @POST("alumnos")
    suspend fun addAlumno(@Body alumno: AlumnoApiModel): Response<AlumnoApiModel>

    @DELETE("alumnos/{id}")
    suspend fun deleteAlumno(@Path("id") id: String): Response<Unit>

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @GET("alumnos")
    suspend fun getAlumnos(@Header("Authorization") token: String):
            Response<List<AlumnoApiModel>>
}