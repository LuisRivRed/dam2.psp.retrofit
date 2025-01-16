package com.psp.model

class DeleteAlumnoUseCase (private val repository: AlumnoRepository) {
    suspend fun invoke(id:Int){
        repository.deleteAlumno(id)
    }
}