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
import com.psp.retrofit.service.AlumnoRepository
import com.psp.retrofit.service.ApiClient
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.psp.retrofit.ui.theme.RetrofitTheme

class MainActivity : ComponentActivity() {
    override  fun onCreate(savedInstanceState: Bundle?) {
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
        takeAlumnos()
    }
    fun takeAlumnos() {
        lifecycleScope.launch {
            val apiService = ApiClient.provideApiService()
            val repository = AlumnoRepository(apiService)

            try {
                val alumnos = repository.getAlumnos() // Este método debería ser suspend
                for (alumno in alumnos) {
                    Log.d("Alumno", alumno.toString())
                }
            } catch (e: Exception) {
                Log.e("Error", "Error al obtener alumnos: ${e.message}")
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
