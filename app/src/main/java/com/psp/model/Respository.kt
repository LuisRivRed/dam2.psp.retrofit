package com.psp.model

import com.psp.data.AlumnoApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Repository {

    suspend fun login(username: String, password: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val response = AlumnoApiClient().apiService.login(Login(username, password))
            if (response.isSuccessful) {
                val token = response.body()?.token
                if (token.isNullOrBlank()) {
                    Result.failure(Exception("Token no encontrado en la respuesta"))
                } else {
                    AlumnoApiClient().setToken(token)
                    Result.success(token)
                }
            } else {
                Result.failure(Exception("Error de autenticación: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAlumnos(token: String): Result<List<Alumno>> = withContext(Dispatchers.IO) {
        try {
            val response = AlumnoApiClient().apiService.getAlumnos()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                Result.failure(Exception("Error al obtener alumnos: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
