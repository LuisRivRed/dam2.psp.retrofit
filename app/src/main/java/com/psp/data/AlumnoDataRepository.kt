package com.psp.data

import com.psp.data.model.Alumno
import com.psp.data.model.AlumnoRepository
import com.psp.data.remote.ApiService
import retrofit2.Response

class AlumnoDataRepository(
    private val apiService: ApiService
): AlumnoRepository {

    override suspend fun getAlumnos(): Response<List<Alumno>> {
        val response = apiService.getAlumnos()

        return if (response.isSuccessful) {
            Response.success(response.body())
        } else {
            Response.error(response.code(), response.errorBody() ?: okhttp3.ResponseBody.create(null, "Error"))
        }
    }

    override suspend fun getAlumno(id: Int): Response<Alumno> {
        val response = apiService.getAlumno(id)

        return if (response.isSuccessful) {
            Response.success(response.body())
        } else {
            Response.error(response.code(), response.errorBody() ?: okhttp3.ResponseBody.create(null, "Error"))
        }
    }

        override suspend fun deleteAlumno(id: Int): Response<Unit> {
        val response = apiService.deleteAlumno(id)

        return if (response.isSuccessful) {
            Response.success(response.body())
        } else {
            Response.error(response.code(), response.errorBody() ?: okhttp3.ResponseBody.create(null, "Error"))
        }
    }

}