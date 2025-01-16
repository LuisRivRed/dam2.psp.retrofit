package com.psp.model

import com.psp.data.AlumnoDataRepository

class CreateAlumnoUseCase() {
    private val repository= AlumnoDataRepository
    suspend fun invoke(alumno:Alumno){
        repository.saveAlumno(alumno)
    }
}