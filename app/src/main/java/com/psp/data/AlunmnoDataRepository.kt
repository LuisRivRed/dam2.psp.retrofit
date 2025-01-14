package com.psp.data

import com.psp.data.model.Alumno
import com.psp.data.model.AlumnoRepository
import com.psp.data.remote.ApiService
import retrofit2.Response

class AlunmnoDataRepository(
    private val apiService: ApiService
): AlumnoRepository {

    override suspend fun getAlumnos(): Response<List<Alumno>> {
        val response = apiService.getAlumnos()
        return Response.success(response.body())
    }

    override suspend fun getAlumnosByName(name: String): Response<List<Alumno>> {
        val response = apiService.getAlumnosByName(name)
        return Response.success(response.body())
    }

}