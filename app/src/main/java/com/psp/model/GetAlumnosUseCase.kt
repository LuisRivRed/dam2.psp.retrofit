package com.psp.model

class GetAlumnosUseCase (private val repository: AlumnoRepository) {
    suspend fun invoke():List<Alumno>{
        return repository.getAlumnos()
    }
}