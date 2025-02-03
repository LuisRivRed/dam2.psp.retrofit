package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.psp.retrofit.service.AlumnoRepository
import com.psp.retrofit.service.ApiClient
import androidx.lifecycle.lifecycleScope
import com.psp.model.Alumno
import kotlinx.coroutines.launch
import com.psp.retrofit.ui.theme.RetrofitTheme

class MainActivity : ComponentActivity() {
    val repository = AlumnoRepository(ApiClient.apiService)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Estado para almacenar la lista de alumnos
            val alumnosState = remember { mutableStateOf<List<Alumno>>(emptyList()) }
            // Estado para almacenar mensajes de error o de status
            val messageState = remember { mutableStateOf("") }

            // Ejecuta las llamadas de red al iniciar la composición
            LaunchedEffect(Unit) {
                // Realiza el login y obtiene el token
                repository.login("admin", "password").fold(
                    onSuccess = { token ->
                        // Llama a getAlumnos pasando el token con el formato "Bearer <token>"
                        repository.getAlumnos(token).fold(
                            onSuccess = { alumnos ->
                                alumnosState.value = alumnos
                                messageState.value = "Alumnos obtenidos: ${alumnos.size}"
                            },
                            onFailure = { error ->
                                messageState.value = "Error al obtener alumnos: ${error.message}"
                                Log.e("MainActivity", "Error al obtener alumnos", error)
                            }
                        )
                    },
                    onFailure = { error ->
                        messageState.value = "Error en login: ${error.message}"
                        Log.e("MainActivity", "Error en login", error)
                    }
                )
            }

            RetrofitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
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
@Composable
fun AlumnoList(alumnos: List<Alumno>, modifier: Modifier = Modifier) {
    if (alumnos.isEmpty()) {
        Text(text = "No hay alumnos para mostrar.")
    } else {
        // Muestra cada alumno. Ajusta la propiedad 'nombre' según tu modelo Alumno.
        alumnos.forEach { alumno ->
            Text(text = "Alumno: ${alumno.nombre}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetrofitTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Ejemplo de alumnos")
        }
    }
}
