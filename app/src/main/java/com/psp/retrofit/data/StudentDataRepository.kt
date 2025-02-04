package com.psp.retrofit.data

import com.psp.retrofit.data.remote.ApiService
import com.psp.retrofit.data.remote.RetrofitClient
import com.psp.retrofit.model.Course
import com.psp.retrofit.model.LoginRequest
import com.psp.retrofit.model.Student
import com.psp.retrofit.model.StudentRepository
import com.psp.retrofit.model.Subject

class StudentDataRepository(
    private val apiService: ApiService
) : StudentRepository {

    suspend fun login(username: String, password: String): Result<String> = try {
        val response = apiService.login(LoginRequest(username, password))
        if (response.isSuccessful) {
            val token = response.body()?.token
            if (token != null) {
                RetrofitClient.setToken(token) // Establecer el token para futuras solicitudes
                Result.success(token)
            } else {
                Result.failure(Exception("Token no encontrado"))
            }
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

    override suspend fun getStudentById(id: Int): Result<Student> = try {
        val response = apiService.requestStudentById(id)
        if (response.isSuccessful) {
            Result.success(response.body() ?: throw Exception("Estudiante no encontrado"))
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getStudentByName(name: String): Result<List<Student>> = try {
        val response = apiService.requestStudentByName(name)
        if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getStudentByEmail(email: String): Result<Student> = try {
        val response = apiService.requestStudentByEmail(email)
        if (response.isSuccessful) {
            Result.success(response.body() ?: throw Exception("Estudiante no encontrado"))
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getStudentsByCourse(course: Course): Result<List<Student>> = try {
        val response = apiService.requestStudentsByCourse(course)
        if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getStudentsBySubject(subject: Subject): Result<List<Student>> = try {
        val response = apiService.requestStudentsBySubject(subject)
        if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun addStudent(student: Student): Result<Student> = try {
        val response = apiService.addStudent(student)
        if (response.isSuccessful) {
            Result.success(response.body() ?: throw Exception("Error al agregar estudiante"))
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun deleteStudent(id: Int): Result<Boolean> = try {
        val response = apiService.deleteStudent(id)
        if (response.isSuccessful) {
            Result.success(response.body() ?: throw Exception("Error al eliminar estudiante"))
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}
