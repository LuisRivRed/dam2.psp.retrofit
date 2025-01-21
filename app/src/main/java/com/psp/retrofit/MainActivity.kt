package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.psp.retrofit.service.AlumnoRepository
import com.psp.retrofit.service.ApiClient
import androidx.lifecycle.lifecycleScope
import com.psp.model.Alumno
import kotlinx.coroutines.launch
import com.psp.retrofit.ui.theme.RetrofitTheme

class MainActivity : ComponentActivity() {
    val apiService = ApiClient.provideApiService()
    val repository = AlumnoRepository(apiService)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        //takeAlumnos()
        takeAlumno()
        addAlumno()
        //deleteAlumno()
    }

    fun takeAlumnos() {
        lifecycleScope.launch {


            try {
                val alumnos = repository.getAlumnos()
                for (alumno in alumnos) {
                    Log.d("Alumno", alumno.toString())
                }
            } catch (e: Exception) {
                Log.e("Error", "Error al obtener alumnos: ${e.message}")
            }
        }
    }

    fun takeAlumno() {
        lifecycleScope.launch {
            try {
                val alumno = repository.getAlumno(1)
                if (alumno != null) {
                    Log.d("Alumno", alumno.toString())
                } else {
                    Log.d("Alumno", "Alumno no encontrado")
                }
            } catch (e: Exception) {
                Log.e("Error", "Error al obtener alumno: ${e.message}")

            }
        }
    }

    fun addAlumno() {
        lifecycleScope.launch {

            val alumno = Alumno(
                id = 10,
                nombre = "juan",
                fechaNacimiento = "2000-01-01",
                curso = com.psp.model.Curso.DAM1,
                email = "john.jay@example.com",
                asignatura = listOf(com.psp.model.Asignatura.PSP, com.psp.model.Asignatura.PMDM)
            )
            try {
                val alumno = repository.addAlumno(alumno)
                if (alumno != null) {
                    Log.d("Alumno", alumno.toString())
                } else {
                    Log.d("Alumno", "Alumno no encontrado")
                }
            } catch (e: Exception) {
                Log.e("Error", "Error al obtener alumno: ${e.message}")
            }
        }

    }
    fun deleteAlumno(){
        lifecycleScope.launch {
            try {
                val alumno = repository.deleteAlumno(2)

                if (alumno) {
                    Log.d("Alumno", "Alumno eliminado")
                } else {
                    Log.d("Alumno", "Alumno no encontrado")
                }
                } catch (e: Exception) {
                Log.e("Error", "Error al obtener alumno: ${e.message}")
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
    RetrofitTheme {
        Greeting("Android")
    }
}
