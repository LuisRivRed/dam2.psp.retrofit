package com.psp.data.remote

import com.psp.model.Course
import com.psp.model.Student
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @GET("students")
    suspend fun getStudents(): Response<List<Student>>

    @GET("students/id/{id}")
    suspend fun getStudentById(@Path("id") id: Int): Response<Student>

    @GET("students/name/{name}")
    suspend fun getStudentByName(@Path("name") name: String): Response<Student>

    @GET("students/course/{course}")
    suspend fun getStudentByCourse(@Path("course") course: Course): Response<List<Student>>

    @POST("student")
    suspend fun newStudent(@Body student: Student): Response<Student>

    @DELETE("student/{id}")
    suspend fun deleteStudent(@Path("id") id: Int): Response<Boolean>
}