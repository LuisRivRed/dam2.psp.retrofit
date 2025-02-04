package com.psp.data.remote.api

import android.util.Log
import com.psp.model.Alumno
import com.psp.model.AuthInterceptor
import com.psp.model.Curso
import com.psp.model.LoginRequest
import com.psp.model.TokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlumnoApiDataSource(
    private val service: ApiService,
    private val authInterceptor: AuthInterceptor
) {

    // Login
    suspend fun login(login: LoginRequest): Result<TokenResponse> {
        return withContext(Dispatchers.IO) { // Llamada en hilo de fondo
            try {
                val response = service.login(login)

                if (response.isSuccessful) {
                    val token = response.body()?.token
                    if (token != null) {
                        Log.d("AlumnoApiDataSource", "Token: $token")
                        authInterceptor.setToken(token)
                        Result.success(TokenResponse(token))
                    } else {
                        Result.failure(Throwable("Token not found in response body"))
                    }
                } else {
                    Log.d("AlumnoApiDataSource", "Login failed with status: ${response.code()}")
                    Result.failure(Throwable("Login failed with status: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    // Obtener alumnos
    suspend fun getAlumnos(): Result<List<Alumno>> {
        return withContext(Dispatchers.IO) { // Llamada en hilo de fondo
            try {
                val response = service.fetchAlumnos()

                if (response.isSuccessful && response.body().isNullOrEmpty().not()) {
                    Result.success(response.body() ?: emptyList())
                } else {
                    Result.failure(Throwable("No alumnos found"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    // Obtener alumnos por curso
    suspend fun getAlumnosByCurso(curso: Curso): Result<List<Alumno>> {
        return withContext(Dispatchers.IO) { // Llamada en hilo de fondo
            try {
                val response = service.fetchAlumnosByCurso(curso.name)

                if (response.isSuccessful && response.body().isNullOrEmpty().not()) {
                    Result.success(response.body() ?: emptyList())
                } else {
                    Result.failure(Throwable("No alumnos found for course: ${curso.name}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    // Obtener alumno por nombre
    suspend fun getAlumnosByName(name: String): Result<Alumno> {
        return withContext(Dispatchers.IO) { // Llamada en hilo de fondo
            try {
                val response = service.fetchAlumnosByName(name)

                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it)
                    } ?: Result.failure(Throwable("Alumno not found"))
                } else {
                    Result.failure(Throwable("No alumno found with name: $name"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    // Agregar un nuevo alumno
    suspend fun addAlumno(alumno: Alumno): Result<Unit> {
        return withContext(Dispatchers.IO) { // Llamada en hilo de fondo
            try {
                val response = service.addAlumno(alumno)
                if (response.isSuccessful) {
                    Result.success(Unit)
                } else {
                    Result.failure(Throwable("Failed to add alumno"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    // Eliminar alumno por ID
    suspend fun deleteAlumnoById(id: Int): Result<Unit> {
        return withContext(Dispatchers.IO) { // Llamada en hilo de fondo
            try {
                val response = service.deleteAlumnoById(id)
                if (response.isSuccessful) {
                    Result.success(Unit)
                } else {
                    Result.failure(Throwable("Failed to delete alumno with ID: $id"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
