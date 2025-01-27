package com.psp.data

import com.psp.model.Alumno
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AlumnoService {

    @GET("alumnos")
    suspend fun requestAlumnos() : Response<List<Alumno>>

    @GET("alumnos/curso/{curso}")
    suspend fun requestAlumnosByCurso(@Path("curso") curso: String): Response<List<Alumno>>

    @GET("alumnos/nombre/{nombre}")
    suspend fun requestAlumnoByNombre(@Path("nombre") nombre: String): Response<Alumno?>

    @POST("alumnos")
    suspend fun addAlumno(@Body alumno: Alumno): Response<Unit>

    @DELETE("alumnos/eliminar/{idAlumno}")
    suspend fun deleteAlumnoById(@Path("idAlumno") idAlumno: Int): Response<Unit>
}