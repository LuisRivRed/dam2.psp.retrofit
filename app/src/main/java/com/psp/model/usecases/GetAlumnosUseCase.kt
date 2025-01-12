package com.psp.model.usecases

import com.psp.model.AlumnoRepository

class GetAlumnosUseCase (
    private val repository: AlumnoRepository
) {
    suspend fun invoke() = repository.getAlumnos()
}