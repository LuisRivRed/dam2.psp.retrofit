package com.psp.model

interface AlumnoRepository {

    suspend fun login(login: LoginRequest): Result<TokenResponse>
    suspend fun getAlumnos(): Result<List<Alumno>>
    suspend fun getAlumnosByCurso(curso: Curso): Result<List<Alumno>>
    suspend fun getAlumnosByName(name: String): Result<Alumno>
    suspend fun addAlumno(alumno: Alumno): Result<Unit>
    suspend fun deleteAlumnoById(id: Int): Result<Unit>

}