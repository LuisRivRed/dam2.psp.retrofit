package com.psp.data.model

import retrofit2.Response

interface AlumnoRepository {
    suspend fun getAlumnos(): Response<List<Alumno>>
    suspend fun getAlumnosByName(nombre: String): Response<List<Alumno>>
}