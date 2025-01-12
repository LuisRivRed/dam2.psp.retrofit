package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.psp.domain.model.Alumno
import com.psp.domain.model.Curso
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var alumnosService: AlumnosService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alumnosService = AlumnosService

        getAlumnos()
        getAlumnosByCurso()
        createAlumno()
        getAlumnoByNombre()
        deleteAlumnoWithGet()
        deleteAlumno()
    }

    fun getAlumnos() {
        lifecycleScope.launch(Dispatchers.IO) {
            val alumnos = alumnosService.getAlumnos()
            alumnos.forEach { alumno ->
                Log.d("Alumno", alumno.toString())
            }
        }
    }

    fun getAlumnosByCurso() {
        lifecycleScope.launch(Dispatchers.IO) {
            val alumnos = alumnosService.getAlumnosByCurso("DAM1")
            alumnos.forEach { alumno ->
                Log.d("Alumno", alumno.toString())
            }
        }
    }

    fun createAlumno() {
        val alumno = Alumno(
            id = 0,
            nombre = "Rubén",
            fechaNacimiento = "01/01/1999",
            curso = Curso.DAM1,
            email = "william.henry.harrison@example-pet-store.com",
            asignaturas = emptyList()
        )
        lifecycleScope.launch(Dispatchers.IO) {
            val alumnoCreado = alumnosService.createAlumno(alumno)
            Log.d("Alumno", alumnoCreado.toString())
        }
    }

    fun getAlumnoByNombre() {
        lifecycleScope.launch(Dispatchers.IO) {
            val alumno = alumnosService.getAlumnoByNombre("Rubén")
            Log.d("Alumno", alumno.toString())
        }
    }

    fun deleteAlumnoWithGet() {
        lifecycleScope.launch(Dispatchers.IO) {
            val mensaje = alumnosService.deleteAlumnoWithGet(1)
            Log.d("Mensaje", mensaje)
        }
    }

    fun deleteAlumno() {
        lifecycleScope.launch(Dispatchers.IO) {
            alumnosService.deleteAlumno(1)
            Log.d("Mensaje", "Alumno eliminado")
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