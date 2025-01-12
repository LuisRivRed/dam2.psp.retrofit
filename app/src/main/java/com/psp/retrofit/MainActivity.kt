package com.psp.retrofit

import android.os.Bundle
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
import com.psp.data.ApiClient
import com.psp.domain.model.Alumno
import com.psp.domain.model.Asignatura
import com.psp.domain.model.Curso
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        main()
    }
}

fun main() {

    val apiService = ApiClient.retrofit
    println("Respuestas de la api: Alumnos\n")

    runBlocking {
        val alumnos = apiService.getAlumnos()
        println("Todos los alumnos: \n $alumnos")

        val nombre = "Alexander Arnold"
        val alumnoNombre = apiService.getAlumnoNombre(nombre)
        println("\nAlumno por nombre: $alumnoNombre")

        val curso = "DAM1"
        val alumnoCurso = apiService.getAlumnoCurso(curso)
        println("\nAlumno por curso: $alumnoCurso")

        val newAlumno = Alumno(
            id = 4,
            nombre = "Enrique",
            fechaNacimiento = "10/12/27",
            curso = Curso.DAW1,
            email = "e@gmail.com",
            asignaturas = listOf(Asignatura.PSP, Asignatura.EIE, Asignatura.PMDM))
        apiService.addAlumno(newAlumno)

        val updatedAlumnosAfterAdd  = apiService.getAlumnos()
        println("Lista de alumnos")

        val idAlumno = 1
        apiService.deleteAlumno(idAlumno)


        val updatedAlumnosAfterDelete  = apiService.getAlumnos()
        println("Lista de alumnos ")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitTheme {
        Greeting("Android")
    }
}