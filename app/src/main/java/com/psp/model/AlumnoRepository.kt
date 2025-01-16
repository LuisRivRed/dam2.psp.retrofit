package com.psp.model

interface AlumnoRepository {

    suspend fun getAlumnos():List<Alumno>
    suspend fun getAlumnoByName(name:String):Alumno?
    suspend fun getAlumnoByCurso(curso:String):List<Alumno>
    suspend fun saveAlumno(alumno:Alumno)
    suspend fun deleteAlumno(id:Int)

}