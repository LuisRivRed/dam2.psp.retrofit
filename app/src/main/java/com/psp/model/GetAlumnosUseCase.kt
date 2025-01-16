package com.psp.model

import com.psp.data.AlumnoDataRepository

class GetAlumnosUseCase {
    private val repository= AlumnoDataRepository
    suspend fun invoke():List<Alumno>{
        return repository.getAlumnos()
    }
}