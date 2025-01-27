package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.psp.data.AlumnoService
import com.psp.data.ApiClient
import com.psp.model.Alumno
import com.psp.model.Curso
import com.psp.model.Asignatura
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val alumnoService: AlumnoService = ApiClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        performAlumnoOperations()
    }

    private fun performAlumnoOperations() {
        val alumnoHandler = AlumnoApiHandler(alumnoService)

        lifecycleScope.launch {
            alumnoHandler.getAlumnos()
            alumnoHandler.getAlumnoByNombre("Roberto")
            alumnoHandler.getAlumnoByCurso("DAM2")
            alumnoHandler.getAlumnoById(1)

            val newAlumno = Alumno(
                id = 4,
                nombre = "Arturo",
                fechaNacimiento = "5/06/2005",
                curso = Curso.DAM2,
                email = "arturo10@gmail.com",
                asignaturas = listOf(Asignatura.PSP, Asignatura.EIE, Asignatura.PMDM, Asignatura.AAD)
            )
            alumnoHandler.addAlumno(newAlumno)

            alumnoHandler.deleteAlumno(1)
        }
    }
}

class AlumnoApiHandler(private val alumnoService: AlumnoService) {

    suspend fun getAlumnos() {
        executeApiCall {
            val alumnos = alumnoService.getAlumnos()
            Log.d("@dev", "Todos los alumnos: $alumnos")
        }
    }

    suspend fun getAlumnoByNombre(nombre: String) {
        executeApiCall {
            val alumno = alumnoService.getAlumnoNombre(nombre)
            Log.d("@dev", "Alumno por nombre ($nombre): $alumno")
        }
    }

    suspend fun getAlumnoByCurso(curso: String) {
        executeApiCall {
            val alumnos = alumnoService.getAlumnoCurso(curso)
            Log.d("@dev", "Alumnos por curso ($curso): $alumnos")
        }
    }

    suspend fun addAlumno(alumno: Alumno) {
        executeApiCall {
            alumnoService.addAlumno(alumno)
            Log.d("@dev", "Alumno añadido: $alumno")
        }
    }

    suspend fun deleteAlumno(id: Int) {
        executeApiCall {
            alumnoService.deleteAlumno(id)
            Log.d("@dev", "Alumno eliminado con id: $id")
        }
    }

    suspend fun getAlumnoById(id: Int) {
        executeApiCall {
            val alumno = alumnoService.getAlumnoId(id)
            Log.d("@dev", "Alumno por id ($id): $alumno")
        }
    }

    private suspend fun executeApiCall(action: suspend () -> Unit) {
        try {
            withContext(Dispatchers.IO) { action() }
        } catch (e: IOException) {
            Log.e("@dev", "Error de red: ${e.message}", e)
        } catch (e: Exception) {
            Log.e("@dev", "Error: ${e.message}", e)
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
    RetrofitTheme {
        Greeting("Android")
    }
}