package com.psp.model

import com.psp.data.AlumnoDataRepository

class DeleteAlumnoUseCase {
    private val repository= AlumnoDataRepository
    suspend fun invoke(id:Int){
        repository.deleteAlumno(id)
    }
}