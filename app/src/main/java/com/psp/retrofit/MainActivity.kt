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
import com.psp.data.AlumnoApiModel
import com.psp.data.ApiClient
import com.psp.model.Asignatura
import com.psp.model.Curso
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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
        fetchAlumnos()
        fetchAlumnoByCurso("DAM1")
        fetchAlumnoPorNombre("Pedro")
        addAlumno(
            alumno = AlumnoApiModel(
                "4",
                "Alejandro",
                "05/06/2001",
                Curso.DAM1,
                "alejandro@gmail.com",
                listOf(Asignatura.PSP, Asignatura.DDI, Asignatura.PMDM)
            )
        )
        deleteAlumno("3")

    }

    private fun fetchAlumnoByCurso(curso: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = ApiClient.retrofit
                val response = apiService.getAlumno(curso.toString())


                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.d("@dev", "Alumno listado por curso: ${response.body()}")
                    } else {
                        Log.e("@dev", "Error: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("@dev", "Error al obtener alumnos: ${e.message}", e)
                }
            }
        }
    }

    private fun fetchAlumnos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = ApiClient.retrofit
                val response = apiService.getAlumnos()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val alumnos = response.body()
                        Log.d("@dev", "Alumnos obtenidos: ${alumnos?.toString()}")
                    } else {
                        Log.e("@dev", "Error al obtener alumnos: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("@dev", "Error al obtener alumnos: ${e.message}", e)
                }
            }
        }
    }

    private fun fetchAlumnoPorNombre(nombre: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = ApiClient.retrofit
                val response = apiService.getAlumnoPorNombre(nombre)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val alumnos = response.body()
                        Log.d("@dev", "Alumnos con nombre $nombre: ${alumnos?.toString()}")
                    } else {
                        Log.e("@dev", "Error al obtener alumnos por nombre: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("@dev", "Error al obtener alumnos por nombre: ${e.message}", e)
                }
            }
        }
    }

    private fun addAlumno(alumno: AlumnoApiModel) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = ApiClient.retrofit
                val response = apiService.addAlumno(alumno)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.d("@dev", "Alumno añadido: ${response.body()}")
                    } else {
                        Log.e("@dev", "Error al añadir alumno: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("@dev", "Error al añadir alumno: ${e.message}", e)
                }
            }
        }
    }

    private fun deleteAlumno(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = ApiClient.retrofit
                val response = apiService.deleteAlumno(id)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.d("@dev", "Alumno eliminado")
                    } else {
                        Log.e("@dev", "Error al eliminar alumno: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("@dev", "Error al eliminar alumno: ${e.message}", e)
                }
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