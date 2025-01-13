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
import com.psp.data.AlumnoApiDataSource
import com.psp.data.ApiClient
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
            RetrofitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        llamadasApi()
    }

    private fun llamadasApi() {
        val dataSource = AlumnoApiDataSource(ApiClient.provideAlumnoService())

        runBlocking {
            val alumnos = dataSource.getAlumnos()
            Log.d("@dev", "GET alumnos")
            Log.d("@dev", alumnos.toString())

            val alumnosCurso = dataSource.getAlumnosByCurso("DAW1")
            Log.d("@dev", "GET alumnos/curso/DAW1")
            Log.d("@dev", alumnosCurso.toString())

            val alumnoNombre = dataSource.getAlumnosByNombre("Pedro")
            Log.d("@dev", "GET alumnos/nombre/Pedro")
            Log.d("@dev", alumnoNombre.toString())

            dataSource.addAlumno(Alumno(20, "Javier", "27/08/2004", Curso.DAW2, "javier.lozher@educa.jcyl.es", listOf(Asignatura.PSP, Asignatura.PMDM)))
            val alumnosAdd = dataSource.getAlumnos()
            Log.d("@dev", "POST alumnos")
            Log.d("@dev", alumnosAdd.toString())

            dataSource.removeAlumno(1)
            val alumnosRemove = dataSource.getAlumnos()
            Log.d("@dev", "DELETE alumnos/eliminar/1")
            Log.d("@dev", alumnosRemove.toString())
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