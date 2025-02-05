package com.psp.data

import com.psp.data.remote.ApiService
import com.psp.model.AlumnoRepository
import com.psp.model.Course
import com.psp.model.LoginRequest
import com.psp.model.Student
import com.psp.model.TokenResponse
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.io.IOException

class StudentDataRepository(private val apiService: ApiService) : AlumnoRepository {

    private var authToken: String? = null

    override suspend fun getStudents(): Response<List<Student>> {
        return Response.success(apiService.getStudents().body())
    }

    override suspend fun getStudentById(id: Int): Response<Student> {
        return Response.success(apiService.getStudentById(id).body())
    }

    override suspend fun getStudentByName(name: String): Response<Student> {
        return Response.success(apiService.getStudentByName(name).body())
    }

    override suspend fun getStudentByCourse(course: Course): Response<List<Student>> {
        return Response.success(apiService.getStudentByCourse(course).body())
    }

    override suspend fun newStudent(student: Student): Response<Student> {
        return Response.success(apiService.newStudent(student).body())
    }

    override suspend fun deleteStudent(id: Int): Response<Boolean> {
        return Response.success(apiService.deleteStudent(id).body())
    }

    suspend fun login(username: String, password: String): Response<TokenResponse> {
        return try {
            val response = apiService.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                response.body()?.let {
                    authToken = it.token
                }
                response
            } else {
                Response.error(
                    response.code(),
                    response.errorBody() ?: "Error desconocido".toResponseBody(null)
                )
            }
        } catch (e: IOException) {
            Response.error(
                500,
                "Error de conexión: ${e.message}".toResponseBody(null)
            )
        }
    }



}