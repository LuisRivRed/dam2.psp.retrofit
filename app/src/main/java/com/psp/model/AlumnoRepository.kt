package com.psp.model

interface AlumnoRepository {

    suspend fun getAlumnos(): List<Alumno>
    suspend fun getAlumnosByCurso(curso: Curso): List<Alumno>
    suspend fun getAlumnosByName(name: String): Alumno
    suspend fun addAlumno(alumno: Alumno)
    suspend fun deleteAlumnoById(id: Int)

}