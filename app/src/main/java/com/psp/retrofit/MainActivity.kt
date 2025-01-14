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
import com.psp.data.ApiClient
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
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        fetchAlumnos()
        encontrarAlumnoId()
    }

    private fun fetchAlumnos() {

        Thread {
            try {
                val apiService = ApiClient.retrofit
                runBlocking {
                    val alumnos = apiService.getAlumnos()
                    Log.d("@dev", alumnos.toString())
                }
            } catch (e: Exception) {
                Log.e("@dev", "Error al obtener alumnos")
            }
        }.start()
    }

    private fun encontrarAlumnoId() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = ApiClient.retrofit
                val alumno = apiService.getAlumno(3)
                withContext(Dispatchers.Main) {
                    Log.d("@dev", alumno.toString())
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("@dev", "Error al obtener alumno", e)
                }
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
}
