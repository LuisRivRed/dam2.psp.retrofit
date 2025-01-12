package com.psp.model.usecases

import com.psp.model.AlumnoRepository

class DeleteAlumnoByIdUseCase(
    private val repository: AlumnoRepository
) {
    suspend fun invoke(id: Int) = repository.deleteAlumnoById(id)
}