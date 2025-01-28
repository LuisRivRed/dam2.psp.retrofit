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
import com.psp.data.AlumnoApiClient
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.runBlocking


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {}

        retrofitEjemplo()
    }
}

fun retrofitEjemplo() {
    val apiAlumno = AlumnoApiClient().apiService
    runBlocking {
        try {
            val alumnos = apiAlumno.getAlumnos()
            Log.d("@prueba", "Lista de alumnos: $alumnos")

            val alumnosDAM1 = apiAlumno.getAlumnosByCurso("DAM1")
            Log.d("@prueba", "Alumnos en el curso DAM1: $alumnosDAM1")

            val alumnoJuan = apiAlumno.getAlumnoByNombre("Juan")
            Log.d("@prueba", "Alumno con nombre 'Juan': $alumnoJuan")


           // ERROR AL AÑADIR EL ALUMNO
            /**
             *
             *             val alumnoDeEjemplo = Alumno(
             *                 id = 6,
             *                 nombre = "Juan",
             *                 fechaNacimiento = "2001-10-08",
             *                 curso = Curso.DAW1,
             *                 email = "juangararr@gmail.com",
             *                 asignaturas = listOf(Asignatura.PSP, Asignatura.DDI, Asignatura.AAD)
             *             )
             *             val nuevoAlumnoResponse = apiAlumno.addAlumno(alumnoDeEjemplo)
             *             Log.d("@prueba", "Alumno añadido: $nuevoAlumnoResponse")
             *
             *             val alumnosActualizados = apiAlumno.getAlumnos()
             *             Log.d("@prueba", "Lista de alumnos tras añadir a Juan: $alumnosActualizados")
             */

            apiAlumno.deleteAlumno(5)
            Log.d("@prueba", "Alumno con ID 5 eliminado")

            val alumnosFinal = apiAlumno.getAlumnos()
            Log.d("@prueba", "Lista de alumnos tras eliminar al alumno con ID 5: $alumnosFinal")

        } catch (e: Exception) {
            Log.e("@prueba", "Error durante la operación de Retrofit: ${e.message}", e)
        }
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