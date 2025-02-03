package com.psp.data


import com.psp.domain.model.Alumno
import retrofit2.Response

class AlumnoDataRepository(private val alumnoApiService: AlumnoApiService) {



    suspend fun getAlumnos(): Response<List<Alumno>> {
        return alumnoApiService.getAlumnos()
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