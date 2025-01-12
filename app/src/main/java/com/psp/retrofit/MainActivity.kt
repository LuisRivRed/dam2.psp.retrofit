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

        main()
    }
}

    fun main() {
        val mainFactory = MainFactory()
        val getAlumnosUseCase = mainFactory.createGetAlumnoUseCase()
        val getAlumnosByCursoUseCase = mainFactory.createGetAlumnoByCursoUseCase()
        val getAlumnosByNameUseCase = mainFactory.createGetAlumnoByNameUseCase()
        val saveAlumnoUseCase = mainFactory.createSaveAlumnoUseCase()
        val deleteAlumnoByIdUseCase = mainFactory.createDeleteAlumnoByIdUseCase()

        //Revisar LOG para ver los resultados y OkkHttp para ver las peticiones :D
        runBlocking {
            val alumnos = getAlumnosUseCase.invoke()
            Log.d("@dev", "Alumnos: $alumnos")

            val alumnosByCurso = getAlumnosByCursoUseCase.invoke(Curso.DAM2)
            Log.d("@dev", "Alumnos by curso: $alumnosByCurso")

            val alumnoByName = getAlumnosByNameUseCase.invoke("Pepe")
            Log.d("@dev", "Alumno by name: $alumnoByName")

            saveAlumnoUseCase.invoke(
                Alumno(4, "Ian", "12/02/2001", Curso.DAM2,
                    "Ian@educa.jcyl.es", listOf(Asignatura.AAD, Asignatura.PSP))
            )
            try {
                val savedAlumno = getAlumnosByNameUseCase.invoke("Ian")
                Log.d("@dev", "Alumno saved succesfully: $savedAlumno")

            } catch (e: Exception) {
                Log.d("@dev", "Alumno was not saved correctly: ${e.message}")
            }

            deleteAlumnoByIdUseCase.invoke(4)
            try {
                getAlumnosByNameUseCase.invoke("Ian")
            } catch (e: Exception) {
                Log.d("@dev", "Alumno deleted succesfully")
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