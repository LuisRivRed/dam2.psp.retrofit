package com.psp.model

class GetAlumnosByCursoUseCase (private val repository: AlumnoRepository) {
    suspend fun invoke(curso:String):List<Alumno>{
        return repository.getAlumnoByCurso(curso)
    }
}