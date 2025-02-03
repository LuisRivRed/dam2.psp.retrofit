package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
    private var authToken: String? = null
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
        loginAndFetchAlumnos()
        takeAlumno(1)
        takeAlumnosByCurso("DAM1")
    }


    fun loginAndFetchAlumnos() {
        lifecycleScope.launch {
            try {
                val loginResult = repository.login("juan@juan", "password")

                loginResult.fold(
                    onSuccess = { token ->
                        authToken = token
                        ApiClient.setToken(token)

                        val alumnosResult = repository.getAlumnos(token)

                        alumnosResult.fold(
                            onSuccess = { alumnos ->
                                alumnos.forEach {
                                    Log.d("MainActivity", "Alumno: $it")
                                }
                            },
                            onFailure = { error ->
                                Log.e("MainActivity", "Error alumnos: ${error.message}")
                            }
                        )
                    },
                    onFailure = { error ->
                        Log.e("MainActivity", "Error login: ${error.message}")
                    }
                )

            } catch (e: Exception) {
                Log.e("MainActivity", "Error general: ${e.message}")
            }
        }
    }

    fun takeAlumno(id: Int) {
        lifecycleScope.launch {
            authToken?.let { token ->
                repository.getAlumnoById(token, id).fold(
                    onSuccess = { alumno ->
                        Log.d("Unico Alumno", "Alumno encontrado: $alumno")
                    },
                    onFailure = { error ->
                        Log.e("Unico alumno", "Error alumno $id: ${error.message}")
                    }
                )
            } ?: Log.e("MainActivity", "No hay token. Haz login primero")
        }
    }

    fun takeAlumnosByCurso(curso: String) {
        lifecycleScope.launch {
            authToken?.let { token ->
                repository.getAlumnosByCurso(token, curso).fold(
                    onSuccess = { alumnos ->
                        alumnos.forEach {
                            Log.d("Curso", "Alumno de $curso: $it")
                        }
                    },
                    onFailure = { error ->
                        Log.e("Curso", "Error curso $curso: ${error.message}")
                    }
                )
            } ?: Log.e("MainActivity", "No hay token. Haz login primero")
        }
    }


    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        val alumnosState = remember { mutableStateOf<List<Alumno>>(emptyList()) }
        val messageState = remember { mutableStateOf("Haz login para ver los alumnos") }

        LaunchedEffect(Unit) {
            val loginResult = repository.login("juan@juan", "password")
            loginResult.fold(
                onSuccess = { token ->
                    ApiClient.setToken(token)
                    val alumnosResult = repository.getAlumnos(token)
                    alumnosResult.fold(
                        onSuccess = { alumnos ->
                            alumnosState.value = alumnos // Actualiza el estado
                            messageState.value = "Alumnos obtenidos: ${alumnos.size}"
                        },
                        onFailure = { error ->
                            messageState.value = "Error: ${error.message}"
                        }
                    )
                },
                onFailure = { error ->
                    messageState.value = "Error en login: ${error.message}"
                }
            )
        }

        Column(modifier = modifier) {
            Text(text = messageState.value)
            Spacer(modifier = Modifier.height(16.dp))
            AlumnoList(alumnos = alumnosState.value)
        }
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
            LazyColumn(modifier = modifier) {
                items(alumnos) { alumno ->
                    AlumnoItem(alumno = alumno)
                }
            }
        }
    }
    @Composable
    fun AlumnoItem(alumno: Alumno) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Nombre: ${alumno.nombre}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Email: ${alumno.email}", style = MaterialTheme.typography.bodyMedium)
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
}