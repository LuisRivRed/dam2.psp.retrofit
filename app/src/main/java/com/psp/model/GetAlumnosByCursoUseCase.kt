package com.psp.model

class GetAlumnosByCursoUseCase(private val repository: AlumnoRepository) {
    suspend operator fun invoke(curso: String): Result<List<Alumno>> =
        repository.getAlumnoByCurso(curso)
}