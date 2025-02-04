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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        obtenerAlumnoPorCurso("DAM1")
        obtenerAlumnoPorNombre("Iker")
        agregarAlumno(
            alumno = AlumnoApiModel(
                "2",
                "Iker",
                "24/04/2004",
                Curso.DAM1,
                "iker@gmail.com",
                listOf(Asignatura.PSP, Asignatura.DDI, Asignatura.PMDM)
            )
        )
        eliminarAlumno("3")
        iniciarSesionYObtenerDatos()
    }

    private fun obtenerAlumnoPorCurso(curso: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = ApiClient.apiService
                val response = apiService.getAlumno(curso)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.d("@dev", "Alumnos listados por curso: ${response.body()}")
                    } else {
                        Log.e("@dev", "Error al obtener alumnos por curso: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("@dev", "Error en la solicitud de alumnos por curso: ${e.message}", e)
                }
            }
        }
    }

    private fun iniciarSesionYObtenerDatos() {
        lifecycleScope.launch {
            val result = AlumnoRepository.login("admin", "password")
            result.onSuccess { token ->
                Log.d("@dev", "Token recibido: $token")

                val alumnosResponse = AlumnoRepository.getAlumnos(token)
                alumnosResponse.onSuccess { alumnos ->
                    Log.d("@dev", "Lista de alumnos: $alumnos")
                }.onFailure {
                    Log.e("@dev", "Error al obtener la lista de alumnos: ${it.localizedMessage}", it)
                }
            }.onFailure {
                Log.e("@dev", "Error en el inicio de sesión: ${it.localizedMessage}", it)
            }
        }
    }

    private fun obtenerAlumnoPorNombre(nombre: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = ApiClient.apiService
                val response = apiService.getAlumnoPorNombre(nombre)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val alumnos = response.body()
                        Log.d("@dev", "Alumnos encontrados con nombre $nombre: ${alumnos?.toString()}")
                    } else {
                        Log.e("@dev", "Error al obtener alumnos por nombre: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("@dev", "Error en la solicitud por nombre: ${e.message}", e)
                }
            }
        }
    }

    private fun agregarAlumno(alumno: AlumnoApiModel) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = ApiClient.apiService
                val response = apiService.addAlumno(alumno)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.d("@dev", "Alumno agregado: ${response.body()}")
                    } else {
                        Log.e("@dev", "Error al agregar alumno: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("@dev", "Error al agregar alumno: ${e.message}", e)
                }
            }
        }
    }

    private fun eliminarAlumno(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = ApiClient.apiService
                val response = apiService.deleteAlumno(id)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.d("@dev", "Alumno eliminado exitosamente")
                    } else {
                        Log.e("@dev", "Error al eliminar alumno: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("@dev", "Error al eliminar alumno: ${e.message}", e)
                }
            }
        }
    }
}