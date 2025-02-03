package com.psp.data

import com.psp.data.model.Alumno
import com.psp.data.model.AlumnoRepository
import com.psp.data.remote.ApiService
import retrofit2.Response

class AlumnoDataRepository(
    private val apiService: ApiService
): AlumnoRepository {

    override suspend fun getAlumnos(): Response<List<Alumno>> {
        return apiService.getAlumnos()
    }

    override suspend fun getAlumnosByName(nombre: String): Response<Alumno> {
        return apiService.getAlumnosByName(nombre)
    }

    override suspend fun getAlumnoByCurso(curso: String): Response<List<Alumno>> {
        return apiService.getAlumnosByCurso(curso)
    }

    override suspend fun getAlumnoById(id: Int): Response<Alumno> {
        return apiService.getAlumnoById(id)
    }

    override suspend fun addAlumno(alumno: Alumno): Response<Alumno> {
        return apiService.addAlumno(alumno)
    }

    override suspend fun deleteAlumno(id: Int): Response<Unit> {
        return apiService.deleteAlumno(id)
    }


}