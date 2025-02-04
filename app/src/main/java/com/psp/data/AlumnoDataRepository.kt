package com.psp.data


import com.psp.domain.model.Alumno
import com.psp.domain.model.Login
import retrofit2.Response

class AlumnoDataRepository(private val alumnoApiService: AlumnoApiService) {

    suspend fun login(username: String, password: String): Result<String> = try {
        val response = alumnoApiService.login(Login(username, password))
        if (response.isSuccessful) {
            val token = response.body()?.tokenAccess ?: throw Exception("Token no encontrado")
            ApiClient.setToken(token)
            Result.success(token)
        } else {
            Result.failure(Exception("Error de autenticación"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getAlumnos(): Response<List<Alumno>> {
        val token = ApiClient.getToken() ?: return Response.error(401, okhttp3.ResponseBody.create(null, "Token no disponible"))
        return alumnoApiService.getAlumnos("Bearer $token")
    }

    suspend fun getAlumnoByNombre(name: String): Response<Alumno>{
        return alumnoApiService.getAlumnoNombre(name)
    }

    suspend fun getAlumnoByCurso(curso: String): Response<List<Alumno>>{
        return alumnoApiService.getAlumnoCurso(curso)
    }

    suspend fun addAlumno(alumno: Alumno): Response<Alumno>{
        return alumnoApiService.addAlumno(alumno)
    }

    suspend fun deleteAlumno(idAlumno: Int): Response<Unit>{
        return alumnoApiService.deleteAlumno(idAlumno)
    }
}