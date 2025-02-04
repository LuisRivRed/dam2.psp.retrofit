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

    val apiService = ApiClient.alumnoApiService
    println("Respuestas de la api: Alumnos\n")

    runBlocking {
        val token = ApiClient.getToken()
        if (token != null) {
            val alumnos = apiService.getAlumnos("Bearer $token") // Pasamos el token en la cabecera
            if (alumnos.isSuccessful) {
                println("Todos los alumnos: \n ${alumnos.body()}")
            } else {
                println("Error al obtener alumnos: ${alumnos.code()} - ${alumnos.message()}")
            }
        } else {
            println("No hay un token disponible. Inicia sesión primero.")
        }

        val nombre = "Alicia" //Cambia este parametro por: Pedro, Pepe, Alicia
        val alumnoNombre = apiService.getAlumnoNombre(nombre)
        println("\nAlumno por nombre: ${alumnoNombre.body()}")

        val curso = "DAM2" //Cambia este parámetro por: DAM1, DAM2, DAW1, DAW2
        val alumnoCurso = apiService.getAlumnoCurso(curso)
        println("\nAlumno por curso: ${alumnoCurso.body()}")

        val newAlumno = Alumno(
            id = 6,
            nombre = "Marcos",
            fechaNacimiento = "14/06/2003",
            curso = Curso.DAW2,
            email = "marcos12@gmail.com",
            asignaturas = listOf(Asignatura.PSP, Asignatura.EIE, Asignatura.PMDM))
        apiService.addAlumno(newAlumno)

        val updatedAlumnosAfterAdd  = apiService.getAlumnos()
        println("\nLista de alumnos mas el nuevo alumno: ${updatedAlumnosAfterAdd.body()}")

        val idAlumno = 1 //Cambia este parámetro para borrar el alumno que desees
        apiService.deleteAlumno(idAlumno)


        val updatedAlumnosAfterDelete  = apiService.getAlumnos()
        println("\nLista de alumnos menos el alumno borrado: ${updatedAlumnosAfterDelete.body()}")
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