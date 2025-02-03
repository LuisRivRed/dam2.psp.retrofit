package com.psp.retrofit.service

import android.util.Log
import com.psp.model.Alumno
import com.psp.model.LoginRequest
import com.psp.retrofit.service.ApiClient.apiService

class AlumnoRepository(private val apiService: ApiService) {
    suspend fun login(username: String, password: String): Result<String> = try {
        val response = apiService.login(LoginRequest(username, password))
        if (response.isSuccessful) {
            val token = response.body()?.token ?: throw Exception("Token no encontrado")
            // Aquí decides almacenar el token manualmente (por ejemplo, en ApiClient o en tu repositorio)
            // No uses el interceptor para inyectarlo automáticamente.
            Result.success(token)
        } else {
            Result.failure(Exception("Error de autenticación"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getAlumnos(token: String): Result<List<Alumno>> = try {
        // Asegúrate de pasar el token en el formato correcto, por ejemplo: "Bearer <tu_token>"
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
