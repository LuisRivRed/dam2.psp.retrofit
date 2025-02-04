package com.psp.data.remote.api

import com.psp.model.Alumno
import com.psp.model.LoginRequest
import com.psp.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // Login ahora retorna Response
    @POST("login")
    suspend fun login(@Body login: LoginRequest): Response<TokenResponse>

    // Obtener todos los alumnos
    @GET("alumnos")
    suspend fun fetchAlumnos(): Response<List<Alumno>>

    // Obtener alumnos por curso
    @GET("alumnos/curso/{curso}")
    suspend fun fetchAlumnosByCurso(@Path("curso") curso: String): Response<List<Alumno>>

    // Obtener alumno por nombre
    @GET("alumnos/nombre/{nombre}")
    suspend fun fetchAlumnosByName(@Path("nombre") name: String): Response<Alumno>

    // Agregar un alumno
    @POST("alumnos")
    suspend fun addAlumno(@Body alumno: Alumno): Response<Unit>

    // Eliminar un alumno por ID
    @DELETE("alumnos/eliminar/{idAlumno}")
    suspend fun deleteAlumnoById(@Path("idAlumno") id: Int): Response<Unit>
}
