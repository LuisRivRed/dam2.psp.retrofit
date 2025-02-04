package com.psp.model

import com.psp.data.AlumnoApiModel
import com.psp.data.AlumnoService
import com.psp.data.RetrofitClient

class AlumnoRepository(private val apiService: AlumnoService) {

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

    suspend fun getAlumnos(token: String): Result<List<AlumnoApiModel>> = try {
        val response = apiService.getAlumnos("Bearer $token")
        if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}