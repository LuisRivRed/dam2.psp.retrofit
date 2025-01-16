package com.psp.model

class GetAlumnoByNombreUseCase (private val repository: AlumnoRepository) {
    suspend fun invoke(name:String):Alumno{
        return repository.getAlumnoByName(name)
    }
}