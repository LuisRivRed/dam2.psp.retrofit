package com.psp.retrofit.data.remote

import com.psp.retrofit.model.Course
import com.psp.retrofit.model.LoginRequest
import com.psp.retrofit.model.Student
import com.psp.retrofit.model.Subject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @GET("alumnos")
    suspend fun requestStudents(): Response<List<Student>>

    @GET("alumnos/id/{id}")
    suspend fun requestStudentById(@Path("id") id: Int): Response<Student>

    @GET("alumnos/nombre/{name}")
    suspend fun requestStudentByName(@Path("name") nombre: String): Response<List<Student>>

    @GET("alumnos/email/{email}")
    suspend fun requestStudentByEmail(@Path("email") email: String): Response<Student>

    @GET("alumnos/curso/{course}")
    suspend fun requestStudentsByCourse(@Path("course") course: Course): Response<List<Student>>

    @GET("alumnos/asignatura/{subject}")
    suspend fun requestStudentsBySubject(@Path("subject") subject: Subject): Response<List<Student>>

    @POST("alumno")
    suspend fun addStudent(@Body student: Student): Response<Student>

    @DELETE("alumno/{id}")
    suspend fun deleteStudent(@Path("id") id: Int): Response<Boolean>
}