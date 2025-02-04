package com.psp.model

class GetAlumnoByNombreUseCase(private val repository: AlumnoRepository) {
    suspend operator fun invoke(name: String): Result<Alumno?> =
        repository.getAlumnoByName(name)
}