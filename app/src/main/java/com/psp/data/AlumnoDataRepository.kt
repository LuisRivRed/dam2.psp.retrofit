package com.psp.data

import com.psp.data.model.Alumno
import com.psp.data.model.LoginRequest
import com.psp.data.remote.ApiClient
import com.psp.data.remote.ApiService
import retrofit2.Response

class AlumnoDataRepository(
    private val apiService: ApiService
) {

    suspend fun login(username: String, password: String): Result<String> = try {
        val response = apiService.login(LoginRequest(username, password))
        if (response.isSuccessful) {
            val token = response.body()?.token ?: throw Exception("Token no encontrado")
            ApiClient.setToken(token)
            Result.success(token)
        } else {
            Result.failure(Exception("Error de autenticación"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getAlumnos(): Result<List<Alumno>> = try {
        val response = apiService.getAlumnos()
        if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getAlumnosByName(nombre: String): Response<Alumno> {
        return apiService.getAlumnosByName(nombre)
    }

    suspend fun getAlumnoByCurso(curso: String): Response<List<Alumno>> {
        return apiService.getAlumnosByCurso(curso)
    }

    suspend fun getAlumnoById(id: Int): Response<Alumno> {
        return apiService.getAlumnoById(id)
    }

    suspend fun addAlumno(alumno: Alumno): Response<Alumno> {
        return apiService.addAlumno(alumno)
    }

    suspend fun deleteAlumno(id: Int): Response<Unit> {
        return apiService.deleteAlumno(id)
    }


}