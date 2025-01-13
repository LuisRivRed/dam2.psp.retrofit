package com.psp.retrofit.api

import com.example.model.Alumno
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentsService {

    @GET("alumnos")
    suspend fun getStudents(): Response<List<Alumno>>

    @GET("alumnos/curso/{course}")
    suspend fun getByCourse(@Path("course") course: String): Response<List<Alumno>>

    @GET("alumnos/nombre/{name}")
    suspend fun getByName(@Path("name") name: String): Response<Alumno>

    @GET("alumnos/id/{id}")
    suspend fun getById(@Path("id") id: String): Response<Alumno>

    @POST("alumnos")
    suspend fun addStudent(@Body student: Alumno): Response<Alumno>

    @GET("alumnos/remove/{id}")
    suspend fun removeById(@Path("id") id: String): Response<Boolean>

}