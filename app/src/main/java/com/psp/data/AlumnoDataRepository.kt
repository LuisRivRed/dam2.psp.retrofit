package com.psp.data

import com.psp.model.Alumno
import com.psp.model.AlumnoRepository

object AlumnoDataRepository: AlumnoRepository {
    private val apiService = AlumnoApiClient.apiService

    suspend fun login(username: String, password: String): Result<String> = try {
        val response = apiService.login(LoginRequest(username, password))
        if (response.isSuccessful) {
            val token = response.body()?.token ?: throw Exception("Token no encontrado")
            AlumnoApiClient.setToken(token)
            Result.success(token)
        } else {
            Result.failure(Exception("Error de autenticación"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getAlumnos():Result<List<Alumno>> = try {
        val response = apiService.getAlumnos()
        if (response.isSuccessful) {
            Result.success(response.body() ?: emptyList())
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
    override suspend fun getAlumnoByName(name:String):Alumno?{
        return apiService.getAlumnoByNombre(name)
    }
    override suspend fun getAlumnoByCurso(curso:String):List<Alumno>{
        return apiService.getAlumnosByCurso(curso)
    }
    override suspend fun saveAlumno(alumno:Alumno){
        apiService.saveAlumno(alumno)
    }
    override suspend fun deleteAlumno(id:Int){
        apiService.deleteAlumno(id)
    }
}