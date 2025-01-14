package com.psp.data

import com.psp.model.Alumno
import com.psp.model.AlumnoRepository
import com.psp.model.Curso
import retrofit2.Response

class AlumnoDataRepository(private val apiService: ApiService) : AlumnoRepository {
    override suspend fun getAlumnos(): Response<List<Alumno>> {
        val response = apiService.getAlumnos()
        return if (response.isSuccessful) {
            response
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun getAlumnoById(id: Int): Response<Alumno> {
        val response = apiService.getAlumnoById(id)
        return if (response.isSuccessful) {
            response
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun gtAlmnoByNombre(nombre: String): Response<Alumno> {
        val response = apiService.getAlumnoByNombre(nombre)
        return if (response.isSuccessful) {
            response
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun getAlumnobyCurso(curso: Curso): Response<List<Alumno>> {
        val response = apiService.getAlumnosByCurso(curso)
        return if (response.isSuccessful) {
            response
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun addAlumno(alumno: Alumno): Response<Alumno> {
        val response = apiService.addAlumno(alumno)
        return if (response.isSuccessful) {
            response
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }

    override suspend fun deleteAlumno(id: Int): Response<Boolean> {
        return Response.success(apiService.deleteAlumno(id).body())
    }
}