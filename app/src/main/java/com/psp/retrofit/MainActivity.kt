package com.psp.retrofit

import android.os.Bundle
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
import com.psp.data.AlumnoDataRepository
import com.psp.data.remote.ApiClient
import com.psp.data.remote.ApiService
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val apiService: ApiService by lazy {
        ApiClient.provideRetrofit().create(ApiService::class.java)
    }
    private lateinit var alumnoDataRepository: AlumnoDataRepository

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
                LaunchedEffect(Unit) {
                    fetchAlumnos()
                }
            }
        }
    }

    private fun fetchAlumnos() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = alumnoDataRepository.getAlumnos()
            if (response.isSuccessful) {
                val alumnos = response.body()
                alumnos?.forEach { alumno ->
                    println(alumno)
                }
            } else {
                println("Error: ${response.code()}")
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