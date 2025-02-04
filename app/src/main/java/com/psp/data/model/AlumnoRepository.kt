package com.psp.data.model

import com.psp.data.remote.ApiClient
import com.psp.data.remote.ApiClient.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AlumnoRepository {

    suspend fun login(username: String, password: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val response = ApiClient.apiService.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                val token = response.body()?.token ?: throw Exception("Token no encontrado")
                ApiClient.setToken(token)
                Result.success(token)
            } else {
                Result.failure(Exception("Error de autenticación: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAlumnos(token : String): Result<List<Alumno>> = try {
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
