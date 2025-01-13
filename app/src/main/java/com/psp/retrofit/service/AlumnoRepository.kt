package com.psp.retrofit.service

import com.psp.model.Alumno

class AlumnoRepository(private val apiService: ApiService) {
    suspend fun getAlumnos():List<Alumno>{
        val response = apiService.requestAlumnos()
        if (response.isSuccessful){
            return response.body() ?: emptyList()
        }else{
            return emptyList()
        }
    }
}