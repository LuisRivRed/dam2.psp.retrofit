package com.psp.domain

import com.psp.domain.model.Alumno
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

@Serializable
data class LoginRequest(val username: String, val password: String)

@Serializable
data class TokenResponse(val token: String)

interface AlumnosApi {

    //Está hecho en base a la API que hice en el anterior ejercicio (por el problema del postman, aunque aqui no sea un problema)

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<TokenResponse>

    @GET("alumnos")
    suspend fun getAlumnos(): Response<List<Alumno>>

    @GET("alumnos/{curso}")
    suspend fun getAlumnosByCurso(@Path("curso") curso: String): Response<List<Alumno>>

    @POST("alumnos")
    suspend fun createAlumno(@Body alumno: Alumno): Response<Alumno>

    @GET("alumnos/{nombre}")
    suspend fun getAlumnoByNombre(@Path("nombre") nombre: String): Response<Alumno>?

    @GET("alumnos/eliminar/{id}")
    suspend fun deleteAlumnoWithGet(@Path("id") id: Int): String

    @DELETE("alumnos/{id}")
    suspend fun deleteAlumno(@Path("id") id: Int)


}