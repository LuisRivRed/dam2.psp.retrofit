package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.psp.data.ApiClient
import com.psp.data.AlumnoApiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val alumnoService = ApiClient.retrofit // Obtenemos directamente el servicio de ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cargar datos de alumnos y mostrarlos en el log
        loadAlumnos()
    }

    private fun loadAlumnos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = alumnoService.getAlumnos()
                if (response.isSuccessful) {
                    val alumnos = response.body()
                    if (alumnos != null) {
                        logAlumnos(alumnos)
                    } else {
                        Log.w("MainActivity", "No se encontraron alumnos.")
                    }
                } else {
                    Log.e("MainActivity", "Error al cargar alumnos: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error al realizar la solicitud: ${e.message}")
            }
        }
    }

    private fun logAlumnos(alumnos: List<AlumnoApiModel>) {
        alumnos.forEach { alumno ->
            Log.d("Alumno", "ID: ${alumno.id}, Nombre: ${alumno.nombre}, Curso: ${alumno.curso}")
        }
    }
}