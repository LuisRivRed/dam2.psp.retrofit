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
import com.psp.kotlin.ApiClient
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }

        main()
    }

    fun main() {
        //Cliente
        val apiClient = ApiClient()
        val apiService = apiClient.apiService

        //Ejemplo de uso de getAlumnos
        runBlocking {
            val response1 = apiService.getAlumnos()
            println(response1.body())

            //Ejemplo de uso de getAlumnosByCurso
            val response2 = apiService.getAlumnosByCurso("DAM1")
            println(response2.body())

            //Ejemplo de uso de getAlumnoByNombre
            val response3 = apiService.getAlumnoByNombre("Ana")
            println(response3.body())

            //Ejemplo de uso de addAlumno
            val alumno = Alumno(5, "Pepa", "1996-07-08", Curso.DAW2, "email5@gmail.com", listOf(Asignatura.PSP, Asignatura.SGE, Asignatura.PMDM))
            apiService.addAlumno(alumno)

            val response4 = apiService.getAlumnos()
            println(response4.body())

            //Ejemplo de uso de deleteAlumno
            val response5 = apiService.deleteAlumno(1) // idAlumno de ejemplo
            println(response5.toString())

            val response6 = apiService.getAlumnos()
            println(response6.body())
        }
    }


    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Composable
    fun GreetingPreview() {
        RetrofitTheme {
            Greeting("Android")
        }
    }
}