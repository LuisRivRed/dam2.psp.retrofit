package com.psp.model

class GetAlumnosUseCase(private val repository: AlumnoRepository) {
    suspend operator fun invoke(): Result<List<Alumno>> =
        repository.getAlumnos()
}