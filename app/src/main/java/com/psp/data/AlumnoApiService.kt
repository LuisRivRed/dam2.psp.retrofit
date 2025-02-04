package com.psp.data

import com.psp.model.Alumno
import com.psp.model.Login
import com.psp.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


interface AlumnoApiService {

    //@GET("/alumnos")
    //suspend fun getAlumnos(): Response<List<Alumno>>

    @GET("/alumnos/cursos/{curso}")
    suspend fun getAlumnosByCurso(@Path("curso") curso: String): Response<List<Alumno>>

    @GET("/alumnos/nombre/{nombre}")
    suspend fun getAlumnoByNombre(@Path("nombre") nombre: String): Response<Alumno>

    @POST("/alumnos/nuevo")
    suspend fun addAlumno(@Body alumno: Alumno): Response<Alumno>

    @DELETE("/alumnos/eliminar/{idAlumno}")
    suspend fun deleteAlumno(@Path("idAlumno") idAlumno: Int): Response<Void>

    @POST("/login")
    suspend fun login(@Body loginRequest:Login): Response<TokenResponse>

    @GET("alumnos")
    suspend fun getAlumnos(): Response<List<Alumno>>

}
