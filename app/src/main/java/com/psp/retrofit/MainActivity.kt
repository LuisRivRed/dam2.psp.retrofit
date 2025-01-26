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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.psp.data.StudentDataRepository
import com.psp.data.remote.ApiClient
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    // Inicialización perezosa del servicio API usando Retrofit
    private val apiService by lazy {
        ApiClient.provideRetrofit().create(com.psp.data.remote.ApiService::class.java)
    }

    // Repositorio que encapsula la lógica para interactuar con los datos de estudiantes
    private val studentRepository by lazy { StudentDataRepository(apiService) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita bordes inmersivos en la aplicación
        enableEdgeToEdge()

        // Configuración de la UI utilizando Jetpack Compose
        setContent {
            RetrofitTheme { // Tema personalizado para la aplicación
                // Estructura principal de la pantalla usando Scaffold
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android", // Muestra un saludo
                        modifier = Modifier.padding(innerPadding) // Ajusta el contenido según el relleno interno
                    )
                }

                // Llama a una función suspendida para obtener datos cuando la pantalla se crea
                LaunchedEffect(Unit) {
                    fetchStudents()
                }
            }
        }
    }

    // Función para obtener la lista de estudiantes del repositorio
    private fun fetchStudents() {
        CoroutineScope(Dispatchers.IO).launch { // Ejecuta en un hilo de fondo
            val response = studentRepository.getStudents()
            if (response.isSuccessful) { // Verifica si la respuesta fue exitosa
                val students = response.body()
                students?.forEach {
                    // Registra cada estudiante en el log
                    Log.d("@dev", it.toString())
                }
            } else {
                // Registra el código de error si la respuesta falla
                Log.d("@dev", "Error: ${response.code()}")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Composable simple que muestra un mensaje de saludo
    Text(
        text = "Hello $name!", // Mensaje mostrado
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // Vista previa del componente Greeting en Android Studio
    RetrofitTheme {
        Greeting("Android")
    }
}
