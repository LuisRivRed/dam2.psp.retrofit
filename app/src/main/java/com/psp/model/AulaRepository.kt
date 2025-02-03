package com.psp.repository

import com.psp.api.ApiService
import com.psp.model.Aula
import com.psp.model.LoginRequest
import com.psp.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AulaRepository(private val apiService: ApiService) {
    suspend fun login(username: String, password: String): Result<String> = withContext(Dispatchers.IO) {
        try {
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
    }

    suspend fun getAulas(): Result<List<Aula>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getAulas()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}