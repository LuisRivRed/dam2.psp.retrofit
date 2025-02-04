package com.psp.model

interface AlumnoRepository {

    suspend fun getAlumnos():Result<List<Alumno>>
    suspend fun getAlumnoByName(name:String):Result<Alumno?>
    suspend fun getAlumnoByCurso(curso:String):Result<List<Alumno>>
    suspend fun saveAlumno(alumno:Alumno)
    suspend fun deleteAlumno(id:Int)

}