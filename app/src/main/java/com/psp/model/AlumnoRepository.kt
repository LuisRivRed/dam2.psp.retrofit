package com.psp.model

import com.psp.data.AlumnoApiModel
import com.psp.data.AlumnoService
import com.psp.data.ApiClient

object AlumnoRepository {
    private val apiService: AlumnoService = ApiClient.apiService

    suspend fun login(username: String, password: String): Result<String> = try {
        val response = apiService.login(LoginRequest(username, password))
        if (response.isSuccessful) {
            val token = response.body()?.token ?: throw Exception("Token no encontrado")
            ApiClient.setToken(token)  // Establece el token
            Result.success(token)
        } else {
            Result.failure(Exception("Error de autenticación"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getAlumnos(token: String): Result<List<AlumnoApiModel>> = try {
        val response = apiService.getAlumnos(token)
        if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

}