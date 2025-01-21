package com.psp.data.model

import retrofit2.Response

interface AlumnoRepository {
    suspend fun getAlumnos(): Response<List<Alumno>>
    suspend fun getAlumno(id: Int): Response<Alumno>
    suspend fun deleteAlumno(id: Int): Response<Unit>
}