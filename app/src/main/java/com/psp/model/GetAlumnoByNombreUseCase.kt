package com.psp.model

import com.psp.data.AlumnoDataRepository

class GetAlumnoByNombreUseCase {
    private val repository= AlumnoDataRepository
    suspend fun invoke(name:String):Alumno{
        return repository.getAlumnoByName(name)
    }
}