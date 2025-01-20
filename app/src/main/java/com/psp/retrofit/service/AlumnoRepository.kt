package com.psp.retrofit.service

import android.util.Log
import com.psp.model.Alumno

class AlumnoRepository(private val apiService: ApiService) {
    suspend fun getAlumnos(): List<Alumno> {
        val response = apiService.requestAlumnos()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            return emptyList()
        }
    }

    suspend fun getAlumno(id: Int): Alumno? {
        val response = apiService.requestAlumno(id)
        if (response.isSuccessful) {
            return response.body()
        } else {
            return null
        }

    }

    suspend fun addAlumno(alumno: Alumno): Alumno? {
        val response = apiService.createAlumno(alumno)
        if (response.isSuccessful) {
            val alumnoCreado = response.body()
            if (alumnoCreado != null) {
                Log.d("Alumno", alumnoCreado.toString())
            } else {
                Log.d("Alumno", "Alumno no encontrado")
            }
        } else {
            Log.e("Error", "Error en la respuesta: ${response.errorBody()?.string()}")
        }
        return response.body()
    }

    suspend fun deleteAlumno(id: Int): Boolean {
        val response = apiService.deleteAlumno(id)
        return response.isSuccessful
    }

}
