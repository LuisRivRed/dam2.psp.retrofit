package com.psp.data

import com.psp.model.Alumno
import com.psp.model.Curso

class AlumnoApiDataSource(private val alumnoService: AlumnoService) {

    suspend fun getAlumnos() : List<Alumno> {
        val result = alumnoService.requestAlumnos()
        return if (result.isSuccessful) {
            result.body()!!
        } else {
            emptyList()
        }
    }

    suspend fun getAlumnosByCurso(curso: String) : List<Alumno> {
        val result = alumnoService.requestAlumnosByCurso(curso)
        return if (result.isSuccessful) {
            result.body()!!
        } else {
            emptyList()
        }
    }

    suspend fun getAlumnosByNombre(nombre: String) : Alumno? {
        val result = alumnoService.requestAlumnoByNombre(nombre)
        return if (result.isSuccessful) {
            result.body()!!
        } else {
            null
        }
    }

    suspend fun addAlumno(alumno: Alumno) {
        alumnoService.addAlumno(alumno)
    }

    suspend fun removeAlumno(alumnoId: Int) {
        alumnoService.deleteAlumnoById(alumnoId)
    }

}