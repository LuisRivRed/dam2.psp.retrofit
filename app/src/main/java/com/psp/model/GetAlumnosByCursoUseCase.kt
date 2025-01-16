package com.psp.model

import com.psp.data.AlumnoDataRepository

class GetAlumnosByCursoUseCase {
    private val repository= AlumnoDataRepository
    suspend fun invoke(curso:String):List<Alumno>{
        return repository.getAlumnoByCurso(curso)
    }
}