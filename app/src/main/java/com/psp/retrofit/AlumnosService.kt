package com.psp.retrofit

import com.psp.data.remote.ApiClient
import com.psp.domain.AlumnosApi
import com.psp.domain.model.Alumno
import kotlinx.serialization.Serializable
import retrofit2.Response

@Serializable
data class LoginRequest(val username: String, val password: String)

@Serializable
data class TokenResponse(val token: String)

object AlumnosService {

    private val alumnosApi: AlumnosApi = ApiClient.provideAlumnosApi()

    suspend fun login(request: LoginRequest): Response<TokenResponse> {
        return alumnosApi.login(request)
    }

    suspend fun getAlumnos(): Response<List<Alumno>> {
        return alumnosApi.getAlumnos()
    }

    suspend fun getAlumnosByCurso(curso: String): Response<List<Alumno>> {
        return alumnosApi.getAlumnosByCurso(curso)
    }

    suspend fun createAlumno(alumno: Alumno): Response<Alumno> {
        return alumnosApi.createAlumno(alumno)
    }

    suspend fun getAlumnoByNombre(nombre: String): Response<Alumno> {
        return alumnosApi.getAlumnoByNombre(nombre)!!
    }

    suspend fun deleteAlumnoWithGet(id: Int): String {
        return alumnosApi.deleteAlumnoWithGet(id)
    }

    suspend fun deleteAlumno(id: Int) {
        alumnosApi.deleteAlumno(id)
    }
}