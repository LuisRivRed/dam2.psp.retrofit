package com.psp.data.remote.api

import com.psp.model.Alumno
import com.psp.model.Curso

class AlumnoApiDataSource(
    private val service: ApiService
) {

    suspend fun getAlumnos(): List<Alumno> {
        return service.fetchAlumnos()
    }
    suspend fun getAlumnosByCurso(curso: Curso): List<Alumno> {
        return service.fetchAlumnosByCurso(curso.name)
    }
    suspend fun getAlumnosByName(name: String): Alumno {
        return service.fetchAlumnosByName(name)
    }
    suspend fun addAlumno(alumno: Alumno) {
        return service.addAlumno(alumno)
    }
    suspend fun deleteAlumnoById(id: Int) {
        return service.deleteAlumnoById(id)
    }

}