package com.psp.data

import com.psp.model.Alumno
import com.psp.model.LoginRequest
import retrofit2.Response

class AlumnoApiDataSource(private val alumnoService: AlumnoService) {

    suspend fun login(username: String, password: String): Result<String> = try {
        val response = alumnoService.login(LoginRequest(username, password))
        if (response.isSuccessful) {
            val token = response.body()?.token ?: throw Exception("Token no encontrado")
            ApiClient.setToken(token)
            Result.success(token)
        } else {
            Result.failure(Exception("Error de autenticación"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getAlumnosLogin(): Result<List<Alumno>> = try {
        val response = alumnoService.getAlumnos(ApiClient.getToken())
        if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }


    suspend fun getAlumnos() : Response<List<Alumno>> {
        return alumnoService.requestAlumnos()
    }

    suspend fun getAlumnosByCurso(curso: String) : Response<List<Alumno>> {
        return alumnoService.requestAlumnosByCurso(curso)
    }

    suspend fun getAlumnosByNombre(nombre: String) : Response<Alumno?> {
        return alumnoService.requestAlumnoByNombre(nombre)
    }

    suspend fun addAlumno(alumno: Alumno):Response<Unit> {
        return alumnoService.addAlumno(alumno)
    }

    suspend fun removeAlumno(alumnoId: Int):Response<Unit> {
        return alumnoService.deleteAlumnoById(alumnoId)
    }

}