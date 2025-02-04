package com.psp.data

import com.psp.model.Alumno
import com.psp.model.AlumnoRepository

object AlumnoDataRepository: AlumnoRepository {
    private val apiService = AlumnoApiClient.apiService

    var authToken: String? = null

    suspend fun login(username: String, password: String): Result<TokenResponse> =
        runCatching {
            val response = apiService.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                response.body()?.also { authToken = it.token }
                    ?: throw Exception("Respuesta exitosa pero sin body")
            } else {
                throw Exception("Error ${response.code()}: ${response.message()}")
            }
        }

    override suspend fun getAlumnos(): Result<List<Alumno>> =
        runCatching {
            val response = apiService.getAlumnos()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                throw Exception("Error ${response.code()}: ${response.message()}")
            }
        }

    override suspend fun getAlumnoByName(name: String): Result<Alumno?> =
        runCatching {
            val response = apiService.getAlumnoByNombre(name)
            if (response.isSuccessful) {
                response.body() // Puede ser null
            } else {
                throw Exception("Error ${response.code()}: ${response.message()}")
            }
        }

    override suspend fun getAlumnoByCurso(curso: String): Result<List<Alumno>> =
        runCatching {
            val response = apiService.getAlumnosByCurso(curso)
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                throw Exception("Error ${response.code()}: ${response.message()}")
            }
        }

    override suspend fun saveAlumno(alumno: Alumno) {
        val response = apiService.saveAlumno(alumno)
        if (!response.isSuccessful) {
            throw Exception("Error ${response.code()}: ${response.message()}")
        }
    }

    override suspend fun deleteAlumno(id: Int) {
        apiService.deleteAlumno(id)
    }
}

