package com.psp.model.usecases

import com.psp.model.AlumnoRepository

class GetAlumnoByNameUseCase(
    private val repository: AlumnoRepository
) {
    suspend fun invoke(name: String) = repository.getAlumnosByName(name)
}