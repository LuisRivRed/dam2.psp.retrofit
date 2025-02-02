package com.psp.data.model

import retrofit2.Response

interface AlumnoRepository {
    suspend fun getAlumnos(): Response<List<Alumno>>
    suspend fun getAlumnosByName(nombre: String): Response<Alumno>
    suspend fun getAlumnoByCurso(curso: String): Response<List<Alumno>>
    suspend fun getAlumnoById(id: Int): Response<Alumno>
    suspend fun addAlumno(alumno: Alumno): Response<Alumno>
    suspend fun deleteAlumno(id: Int): Response<Unit>

}