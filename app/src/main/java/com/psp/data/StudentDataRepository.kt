package com.psp.data

import com.psp.data.remote.ApiService
import com.psp.model.Course
import com.psp.model.LoginRequest
import com.psp.model.Student
import com.psp.model.StudentRepository
import com.psp.model.TokenResponse
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.io.IOException

class StudentDataRepository(private val apiService: ApiService) : StudentRepository {

    private var authToken: String? = null

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

    override suspend fun getStudents(): Response<List<Student>> {
        return try {
            val response = apiService.getStudents()
            if (response.isSuccessful) {
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

    override suspend fun getStudentById(id: Int): Response<Student> {
        return try {
            val response = apiService.getStudentById(id)
            if (response.isSuccessful) {
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

    override suspend fun getStudentByName(name: String): Response<Student> {
        return try {
            val response = apiService.getStudentByName(name)
            if (response.isSuccessful) {
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

    override suspend fun getStudentByCourse(course: Course): Response<List<Student>> {
        return try {
            val response = apiService.getStudentByCourse(course)
            if (response.isSuccessful) {
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

    override suspend fun newStudent(student: Student): Response<Student> {
        return try {
            val response = apiService.newStudent(student)
            if (response.isSuccessful) {
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

    override suspend fun deleteStudent(id: Int): Response<Boolean> {
        return try {
            val response = apiService.deleteStudent(id)
            if (response.isSuccessful) {
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