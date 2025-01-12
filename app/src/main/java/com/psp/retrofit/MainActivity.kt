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
import com.psp.data.AlumnoApiClient
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.runBlocking
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {}

        retrofitSimulate()
    }
}

fun retrofitSimulate() {
    //Instancia para usar la api
    val apiService = AlumnoApiClient().apiService

    //Simulación de uso
    runBlocking {
        try{
            //Lista de todos los alumnos
            Log.d("@retrofit","${apiService.getAlumnos()}")

            //Lista de alumnos filtrados por curso
            Log.d("@retrofit","${apiService.getAlumnosByCurso("DAM1")}")

            //Alumno recogido por nombre
            Log.d("@retrofit","${apiService.getAlumnoByNombre("Kai")}")

            //Agregar un alumno
            val alumnoDeEjemplo = Alumno(
                5, "Robin",
                "2001-04-15",
                Curso.DAW1, "robin@email.com",
                listOf(Asignatura.PSP, Asignatura.DDI, Asignatura.AAD))
            Log.d("@retrofit","${apiService.saveAlumno(alumnoDeEjemplo)}")
            //Vista del cambio despues de agregar un alumno
            Log.d("@retrofit","${apiService.getAlumnos()}")

            //Eliminar el alumno que acabo de guardar
            apiService.deleteAlumno(5)
            //Vista del cambio despues de eliminar un alumno
            Log.d("@retrofit","${apiService.getAlumnos()}")
        }catch(e: IOException) {
            Log.d("@retrofit", "Error de conexión: {$e}")
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