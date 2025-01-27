package com.psp.data

import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import retrofit2.Response

class AlumnoApiDataSource(private val alumnoService: AlumnoService) {

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