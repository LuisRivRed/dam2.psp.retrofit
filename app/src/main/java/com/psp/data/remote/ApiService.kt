package com.psp.data.remote

import com.psp.model.Course
import com.psp.model.Student
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// Interfaz que define los endpoints para realizar operaciones CRUD sobre estudiantes en una API REST
interface ApiService {
    /*
        @POST("login")
        suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>
    */
    // Obtiene la lista completa de estudiantes
    @GET("students")
    suspend fun getStudents(): Response<List<Student>>

    // Obtiene un estudiante específico por su ID
    @GET("students/id/{id}")
    suspend fun getStudentById(@Path("id") id: Int): Response<Student>

    // Obtiene un estudiante específico por su nombre
    @GET("students/name/{name}")
    suspend fun getStudentByName(@Path("name") name: String): Response<Student>

    // Obtiene todos los estudiantes que están inscritos en un curso específico
    @GET("students/course/{course}")
    suspend fun getStudentByCourse(@Path("course") course: Course): Response<List<Student>>

    // Crea un nuevo estudiante en el sistema
    @POST("student")
    suspend fun newStudent(@Body student: Student): Response<Student>

    // Elimina un estudiante del sistema usando su ID
    @DELETE("student/{id}")
    suspend fun deleteStudent(@Path("id") id: Int): Response<Boolean>
}
