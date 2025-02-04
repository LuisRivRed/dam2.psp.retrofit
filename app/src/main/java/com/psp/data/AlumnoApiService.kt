package com.psp.data



import com.psp.domain.model.Alumno
import com.psp.domain.model.Login
import com.psp.domain.model.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AlumnoApiService {

    @POST("login")
    suspend fun login(@Body loginRequest: Login): Response<Token>

    @GET("/alumnos")
    suspend fun getAlumnos(@Header("Authorization") token: String): Response<List<Alumno>>

    @GET("/alumnos/nombre/{nombre}")
    suspend fun getAlumnoNombre(@Path("nombre") nombre: String): Response<Alumno>


    @GET("/alumnos/curso/{curso}")
    suspend fun getAlumnoCurso(@Path("curso") curso: String): Response<List<Alumno>>

    @POST("/alumnos")
    suspend fun addAlumno(@Body alumno: Alumno): Response<Alumno>

    @DELETE("/alumnos/eliminar/{idAlumno}")
    suspend fun deleteAlumno(@Path("idAlumno") idAlumno: Int): Response<Unit>
}