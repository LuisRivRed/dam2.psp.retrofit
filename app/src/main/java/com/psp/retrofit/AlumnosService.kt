package com.psp.retrofit

import com.psp.data.remote.ApiClient
import com.psp.domain.AlumnosApi
import com.psp.domain.model.Alumno

object AlumnosService {

    val alumnosApi: AlumnosApi = ApiClient.retrofit.create(AlumnosApi::class.java)

    suspend fun getAlumnos(): List<Alumno> {
        return alumnosApi.getAlumnos()
    }

    suspend fun getAlumnosByCurso(curso: String): List<Alumno> {
        return alumnosApi.getAlumnosByCurso(curso)
    }

    suspend fun createAlumno(alumno: Alumno): Alumno {
        return alumnosApi.createAlumno(alumno)
    }

    suspend fun getAlumnoByNombre(nombre: String): Alumno? {
        return alumnosApi.getAlumnoByNombre(nombre)
    }

    suspend fun deleteAlumnoWithGet(id: Int): String {
        return alumnosApi.deleteAlumnoWithGet(id)
    }

    suspend fun deleteAlumno(id: Int) {
        alumnosApi.deleteAlumno(id)
    }
}