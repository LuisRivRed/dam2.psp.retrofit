package com.psp.data

import com.psp.model.Alumno
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("students")
    suspend fun requestStudents(): Response<List<Alumno>>

    @GET("students/course/{course}")
    suspend fun requestStudentsByCourse(@Path("course") course: String): Response<List<Alumno>>

    @GET("students/name/{name}")
    suspend fun requestStudentByName(@Path("name") name: String): Response<Alumno>

    @POST("students")
    suspend fun addStudent(@Body student: Alumno): Response<Alumno>

    @DELETE("students/delete/{studentId}")
    suspend fun deleteStudentById(@Path("studentId") studentId: String): Response<Boolean>

}
