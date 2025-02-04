package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.psp.data.AlumnoApiClient
import com.psp.data.AlumnoDataRepository
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.CreateAlumnoUseCase
import com.psp.model.Curso
import com.psp.model.DeleteAlumnoUseCase
import com.psp.model.GetAlumnoByNombreUseCase
import com.psp.model.GetAlumnosByCursoUseCase
import com.psp.model.GetAlumnosUseCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {}

        loginUser()

        //retrofitGetAlumnos()
        //retrofitGetAlumnosByCurso()
        //retrofitGetAlumnoByNombre()
        //retrofitSaveAlumno()
        //retrofitDeleteAlumno()
    }

    private fun loginUser() {
        lifecycleScope.launch {
            // Realizamos el login y, en caso de éxito, obtenemos la lista de alumnos.
            AlumnoDataRepository.login("admin", "password")
                .onSuccess { tokenResponse ->
                    val token = tokenResponse.token
                    if (token.isNotEmpty()) {
                        AlumnoApiClient.setToken(token)
                        Log.d("@dev", "Login exitoso, token: $token")
                            retrofitGetAlumnos()
                        retrofitGetAlumnosByCurso()
                        retrofitGetAlumnoByNombre()
                        retrofitSaveAlumno()
                        retrofitDeleteAlumno()

                    } else {
                        Log.e("@dev", "Error: el token recibido es nulo o vacío")
                    }
                }
                .onFailure { e ->
                    Log.e("@dev", "Error en el login: ${e.localizedMessage}", e)
                }
        }
    }
}


fun retrofitGetAlumnos() {
    runBlocking {
        GetAlumnosUseCase(AlumnoDataRepository).invoke()
            .onSuccess { alumnos ->
                Log.d("@retrofit", "Lista de Alumnos:")
                alumnos.forEach { alumno ->
                    Log.d("@retrofit", "$alumno")
                }
            }
            .onFailure { error ->
                Log.d("@retrofit", "Error al obtener alumnos: $error")
            }
    }
}

fun retrofitGetAlumnosByCurso() {
    runBlocking {
        GetAlumnosByCursoUseCase(AlumnoDataRepository).invoke("DAM1")
            .onSuccess { alumnos ->
                Log.d("@retrofit", "Lista de Alumnos filtrados por curso:")
                alumnos.forEach { alumno ->
                    Log.d("@retrofit", "$alumno")
                }
            }
            .onFailure { error ->
                Log.d("@retrofit", "Error al obtener alumnos por curso: $error")
            }
    }
}

fun retrofitGetAlumnoByNombre() {
    runBlocking {
        GetAlumnoByNombreUseCase(AlumnoDataRepository).invoke("Kai")
            .onSuccess { alumno ->
                Log.d("@retrofit", "Datos de Alumno por nombre:")
                Log.d("@retrofit", "$alumno")
            }
            .onFailure { error ->
                Log.d("@retrofit", "Error al obtener alumno por nombre: $error")
            }
    }
}

fun retrofitSaveAlumno() {
    runBlocking {
        try {
            val alumno = Alumno(
                id = 5,
                nombre = "Ian",
                fechaNacimiento = "2004/05/10",
                curso = Curso.DAM2,
                email = "ian@example.com",
                asignaturas = listOf(Asignatura.PSP, Asignatura.SGE, Asignatura.AAD)
            )
            CreateAlumnoUseCase(AlumnoDataRepository).invoke(alumno)
            Log.d("@retrofit", "Alumno guardado correctamente")
            GetAlumnosUseCase(AlumnoDataRepository).invoke()
                .onSuccess { alumnos ->
                    Log.d("@retrofit", "Lista de alumnos tras agregar uno nuevo:")
                    alumnos.forEach { alumnoItem ->
                        Log.d("@retrofit", "$alumnoItem")
                    }
                }
                .onFailure { error ->
                    Log.d("@retrofit", "Error al obtener alumnos: $error")
                }
        } catch (e: Exception) {
            Log.d("@retrofit", "Error al guardar alumno: $e")
        }
    }
}

fun retrofitDeleteAlumno() {
    runBlocking {
        try {
            DeleteAlumnoUseCase(AlumnoDataRepository).invoke(5)
            Log.d("@retrofit", "Alumno eliminado correctamente")
            GetAlumnosUseCase(AlumnoDataRepository).invoke()
                .onSuccess { alumnos ->
                    Log.d("@retrofit", "Lista de alumnos tras eliminar:")
                    alumnos.forEach { alumno ->
                        Log.d("@retrofit", "$alumno")
                    }
                }
                .onFailure { error ->
                    Log.d("@retrofit", "Error al obtener alumnos: $error")
                }
        } catch (e: Exception) {
            Log.d("@retrofit", "Error al eliminar alumno: $e")
        }
    }
}


