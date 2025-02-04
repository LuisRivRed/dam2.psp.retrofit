package com.psp.data

import android.util.Log
import com.psp.data.remote.api.AlumnoApiDataSource
import com.psp.model.Alumno
import com.psp.model.AlumnoRepository
import com.psp.model.Curso
import com.psp.model.LoginRequest
import com.psp.model.TokenResponse

class AlumnoDataRepository(
    private val remoteDataSource : AlumnoApiDataSource
) : AlumnoRepository {

    // Login modificado para trabajar con Result
    override suspend fun login(login: LoginRequest): Result<TokenResponse> {
        return try {
            Log.d("AlumnoDataRepository", "Login: ${login.username}")
            // Obtener el resultado del login
            val result = remoteDataSource.login(login)
            if (result.isSuccess) {
                val token = result.getOrNull()?.token ?: throw Exception("Token no encontrado")
                Result.success(TokenResponse(token))
            } else {
                // En caso de fallo, se pasa la excepción del error
                Result.failure(Exception("Error en login"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Obtener los alumnos
    override suspend fun getAlumnos(): Result<List<Alumno>> {
        return try {
            remoteDataSource.getAlumnos()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Obtener los alumnos por curso
    override suspend fun getAlumnosByCurso(curso: Curso): Result<List<Alumno>> {
        return try {
            remoteDataSource.getAlumnosByCurso(curso)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Obtener un alumno por nombre
    override suspend fun getAlumnosByName(name: String): Result<Alumno> {
        return try {
            remoteDataSource.getAlumnosByName(name)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Agregar un nuevo alumno
    override suspend fun addAlumno(alumno: Alumno): Result<Unit> {
        return try {
            remoteDataSource.addAlumno(alumno)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Eliminar un alumno por ID
    override suspend fun deleteAlumnoById(id: Int): Result<Unit> {
        return try {
            remoteDataSource.deleteAlumnoById(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

