package com.psp.model

import com.api.model.Aula
import com.psp.model.LoginRequest
import com.psp.remote.ApiClient
import com.psp.remote.ApiClient.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AulaRepository {

    suspend fun login(username: String, password: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.login(LoginRequest(username, password))
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

    suspend fun getAulas(token: String): Result<List<Aula>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getAulas(token)
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAulaById(id: Int): Result<Aula> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getAulasbyid(id)
            if (response.isSuccessful) {
                Result.success(response.body() ?: throw Exception("Aula no encontrada"))
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteAula(id: Int): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.deleteAula(id)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}