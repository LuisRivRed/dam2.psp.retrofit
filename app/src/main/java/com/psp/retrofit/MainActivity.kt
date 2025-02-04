package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.psp.data.AlumnoDataRepository
import com.psp.data.model.Alumno
import com.psp.data.model.Asignatura
import com.psp.data.model.Curso
import com.psp.data.remote.ApiClient
import com.psp.data.remote.ApiService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        }
        main()
    }

    private fun main() {
        val apiService = ApiClient.provideApi()
        val alumnoRepository = AlumnoDataRepository(apiService)

        runBlocking {
            // Obtener el listado de alumnos mediante autenticación
            val result = alumnoRepository.login("admin", "password")
            result.onSuccess { token ->
                Log.d("@dev", "Token recibido: $token")

                val alumnosResponse = alumnoRepository.getAlumnos()
                alumnosResponse.onSuccess { alumnos ->
                    Log.d("@dev", "Alumnos: $alumnos")
                }.onFailure { e ->
                    Log.e("@dev", "Error al obtener alumnos: ${e.localizedMessage}", e)
                }
            }.onFailure { e ->
                Log.e("@dev", "Error en el login: ${e.localizedMessage}", e)
            }

            // Obtener un alumno por nombre
            val response2 = alumnoRepository.getAlumnosByName("Marcos")
            if (response2.isSuccessful) {
                Log.d("@Dev", "Alumno encontrado: ${response2.body()}")
            } else {
                Log.e(
                    "@Dev",
                    "Error al obtener alumno: ${response2.code()} - ${
                        response2.errorBody()?.string()
                    }"
                )
            }

            // Obtener un alumno por ID
            val response3 = alumnoRepository.getAlumnoById(3)
            if (response3.isSuccessful) {
                Log.d("@Dev", "Alumno con ID 3: ${response3.body()}")
            } else {
                Log.e(
                    "@Dev",
                    "Error al obtener alumno por ID: ${response3.code()} - ${
                        response3.errorBody()?.string()
                    }"
                )
            }

            // Agregar un nuevo alumno
            val nuevoAlumno = Alumno(
                id = 0, // Se generará un nuevo ID automáticamente
                nombre = "Carlos",
                fechaNacimiento = "2002-05-16",
                curso = Curso.DAM1,
                email = "carlos@mail.com",
                asignaturas = listOf(Asignatura.PSP, Asignatura.AAD)
            )

            val response5 = alumnoRepository.addAlumno(nuevoAlumno)
            if (response5.isSuccessful) {
                Log.d("@Dev", "Alumno añadido: ${response5.body()}")
            } else {
                Log.e(
                    "@Dev",
                    "Error al añadir alumno: ${response5.code()} - ${
                        response5.errorBody()?.string()
                    }"
                )
            }

            // Eliminar un alumno por ID
            val idToDelete = 2
            val response6 = alumnoRepository.deleteAlumno(idToDelete)
            if (response6.isSuccessful) {
                Log.d("@Dev", "Alumno con ID $idToDelete eliminado")
            } else {
                Log.e(
                    "@Dev",
                    "Error al eliminar alumno: ${response6.code()} - ${
                        response6.errorBody()?.string()
                    }"
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}