package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.psp.data.AlumnoApiModel
import com.psp.data.ApiClient
import com.psp.model.AlumnoRepository
import com.psp.model.Asignatura
import com.psp.model.Curso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        iniciarSesionYObtenerDatos()  // Primero, hacer login y obtener el token
    }

    private fun iniciarSesionYObtenerDatos() {
        lifecycleScope.launch {
            // Primero, realiza el login para obtener el token
            val result = AlumnoRepository.login("admin", "password")
            result.onSuccess { token ->
                Log.d("@dev", "Token recibido: $token")

                val alumno = AlumnoApiModel(
                    "3",
                    "Iker",
                    "24/04/2004",
                    Curso.DAM1,
                    "iker@gmail.com",
                    asignaturas = listOf(Asignatura.PSP, Asignatura.DDI, Asignatura.PMDM)
                )
                agregarAlumno(alumno)



                // Una vez que tengas el token, realiza las solicitudes que necesitan autorización
                // Secuencialmente, esperar que una termine antes de la siguiente
                obtenerAlumnoPorCurso("DAM1")
                obtenerAlumnoPorNombre("Iker")

                // Agregar alumno y esperar que termine antes de eliminarlo

                eliminarAlumno("3")  // Esto se ejecutará solo después de agregar el alumno
            }.onFailure {
                Log.e("@dev", "Error en el inicio de sesión: ${it.localizedMessage}", it)
            }
        }
    }

    private suspend fun obtenerAlumnoPorCurso(curso: String) {
        try {
            val apiService = ApiClient.apiService
            val response = apiService.getAlumno(curso)

            if (response.isSuccessful) {
                Log.d("@dev", "Alumnos listados por curso: ${response.body()}")
            } else {
                Log.e("@dev", "Error al obtener alumnos por curso: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("@dev", "Error en la solicitud de alumnos por curso: ${e.message}", e)
        }
    }

    private suspend fun obtenerAlumnoPorNombre(nombre: String) {
        try {
            val apiService = ApiClient.apiService
            val response = apiService.getAlumnoPorNombre(nombre)

            if (response.isSuccessful) {
                val alumnos = response.body()
                Log.d("@dev", "Alumnos encontrados con nombre $nombre: ${alumnos?.toString()}")
            } else {
                Log.e("@dev", "Error al obtener alumnos por nombre: ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("@dev", "Error en la solicitud por nombre: ${e.message}", e)
        }
    }

    private suspend fun agregarAlumno(alumno: AlumnoApiModel) {
        try {
            val apiService = ApiClient.apiService
            val response = apiService.addAlumno(alumno)

            if (response.isSuccessful) {
                Log.d("@dev", "Alumno agregado: ${response.body()}")
            } else {
                Log.e("@dev", "Error al agregar alumno: ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("@dev", "Error al agregar alumno: ${e.message}", e)
        }
    }

    private suspend fun eliminarAlumno(id: String) {
        try {
            val apiService = ApiClient.apiService
            val response = apiService.deleteAlumno(id)

            if (response.isSuccessful) {
                Log.d("@dev", "Alumno eliminado exitosamente")
            } else {
                Log.e("@dev", "Error al eliminar alumno: ${response.message()}")
            }

        } catch (e: Exception) {
            Log.e("@dev", "Error al eliminar alumno: ${e.message}", e)
        }
    }
}