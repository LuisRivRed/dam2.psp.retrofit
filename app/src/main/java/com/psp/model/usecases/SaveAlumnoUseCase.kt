package com.psp.model.usecases

import com.psp.model.Alumno
import com.psp.model.AlumnoRepository

class SaveAlumnoUseCase(
    private val repository: AlumnoRepository
) {
    suspend fun invoke(alumno: Alumno) = repository.addAlumno(alumno)
}