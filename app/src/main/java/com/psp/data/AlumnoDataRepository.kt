package com.psp.data

import com.psp.model.Alumno
import com.psp.model.AlumnoRepository

object AlumnoDataRepository: AlumnoRepository {
    private val apiService = AlumnoApiClient().apiService

    override suspend fun getAlumnos():List<Alumno>{
        return apiService.getAlumnos()
    }
    override suspend fun getAlumnoByName(name:String):Alumno?{
        return apiService.getAlumnoByNombre(name)
    }
    override suspend fun getAlumnoByCurso(curso:String):List<Alumno>{
        return apiService.getAlumnosByCurso(curso)
    }
    override suspend fun saveAlumno(alumno:Alumno){
        apiService.saveAlumno(alumno)
    }
    override suspend fun deleteAlumno(id:Int){
        apiService.deleteAlumno(id)
    }
}