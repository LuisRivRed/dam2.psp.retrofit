package com.psp.retrofit.data

import com.psp.retrofit.data.remote.ApiService
import com.psp.retrofit.data.remote.RetrofitClient
import com.psp.retrofit.model.Course
import com.psp.retrofit.model.Student
import com.psp.retrofit.model.StudentRepository
import com.psp.retrofit.model.Subject
import retrofit2.Response

class StudentDataRepository(
    private val apiService: ApiService
) : StudentRepository {

    suspend fun login(username: String, password: String): Result<String> = try {
        val response = apiService.login(LoginRequest(username, password))
        if (response.isSuccessful) {
            val token = response.body()?.token ?: throw Exception("Token no encontrado")
            RetrofitClient.setToken(token)
            Result.success(token)
        } else {
            Result.failure(Exception("Error de autenticación"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getStudents(): Result<List<Student>> = try {
        val response = apiService.requestStudents()
        if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getStudentById(id: Int): Response<Student> {
        val response = apiService.requestStudentById(id)
        return Response.success(response.body())
    }

    override suspend fun getStudentByName(name: String): Response<List<Student>> {
        val response = apiService.requestStudentByName(name)
        return Response.success(response.body())
    }

    override suspend fun getStudentByEmail(email: String): Response<Student> {
        val response = apiService.requestStudentByEmail(email)
        return Response.success(response.body())
    }

    override suspend fun getStudentsByCourse(course: Course): Response<List<Student>> {
        val response = apiService.requestStudentsByCourse(course)
        return Response.success(response.body())
    }

    override suspend fun getStudentsBySubject(subject: Subject): Response<List<Student>> {
        val response = apiService.requestStudentsBySubject(subject)
        return Response.success(response.body())
    }

    override suspend fun addStudent(student: Student): Response<Student> {
        val response = apiService.addStudent(student)
        return Response.success(response.body())
    }

    override suspend fun deleteStudent(id: Int): Response<Boolean> {
        val response = apiService.deleteStudent(id)
        return Response.success(response.body())
    }
}