package com.psp.data

import com.psp.domain.model.Alumno
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AlumnoApiService {

    @GET("/alumnos")
    suspend fun getAlumnos(): Response<List<Alumno>>

    @GET("/alumnos/nombre/{nombre}")
    suspend fun getAlumnoNombre(@Path("nombre") nombre: String): Response<Alumno>


    @GET("/alumnos/curso/{curso}")
    suspend fun getAlumnoCurso(@Path("curso") curso: String): Response<List<Alumno>>

    @POST("/alumnos")
    suspend fun addAlumno(@Body alumno: Alumno): Response<Alumno>

    @DELETE("/alumnos/eliminar/{idAlumno}")
    suspend fun deleteAlumno(@Path("idAlumno") idAlumno: Int): Response<Unit>
}