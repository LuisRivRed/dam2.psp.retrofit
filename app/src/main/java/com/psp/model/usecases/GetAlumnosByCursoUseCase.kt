package com.psp.model.usecases

import com.psp.model.AlumnoRepository
import com.psp.model.Curso

class GetAlumnosByCursoUseCase(
    private val repository: AlumnoRepository
) {
    suspend fun invoke(curso: Curso) = repository.getAlumnosByCurso(curso)
}