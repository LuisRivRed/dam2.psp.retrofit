package com.psp.model

class CreateAlumnoUseCase(private val repository: AlumnoRepository) {
    suspend fun invoke(alumno:Alumno){
        repository.saveAlumno(alumno)
    }
}