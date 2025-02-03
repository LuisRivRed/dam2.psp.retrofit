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
            Result.success(token)
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getAlumnos(token: String): Result<List<Alumno>> = try {
        val response = apiService.getAlumnos(("Bearer $token"))
        Log.d("AlumnoRepository", "Token enviado: $token")
        if (response.isSuccessful) {
            Log.d("AlumnoRepository", "Respuesta exitosa: ${response.body()}")
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
    suspend fun getAlumnoById(token: String, id: Int): Result<Alumno> = try {
        val response = apiService.getAlumnoById("Bearer $token", id)
        if (response.isSuccessful) {
            val alumno = response.body()
            if (alumno != null) {
                Result.success(alumno)
            } else {
                Result.failure(Exception("Alumno no encontrado"))
            }
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getAlumnosByCurso(token: String, curso: String): Result<List<Alumno>> = try {
        val response = apiService.getAlumnosByCurso("Bearer $token", curso)
        if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

}
