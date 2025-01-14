package com.psp.model

import retrofit2.Response

interface AlumnoRepository {

    suspend fun getAlumnos(): Response<List<Alumno>>
    suspend fun getAlumnoById(id:Int): Response<Alumno>
    suspend fun getAlumnoByNombre(nombre:String): Response<Alumno>
    suspend fun getAlumnobyCurso(curso:Curso): Response<List<Alumno>>
    suspend fun addAlumno(alumno: Alumno): Response<Alumno>
    suspend fun deleteAlumno(id:Int): Response<Boolean>
}