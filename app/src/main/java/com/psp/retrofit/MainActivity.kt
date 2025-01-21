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
import com.psp.data.AlumnoDataRepository
import com.psp.data.remote.ApiClient
import com.psp.data.remote.ApiService
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
            main()
        }
    }

    private fun main() {
        val apiService = ApiClient.provideApi().create(ApiService::class.java)
        AlumnoDataRepository(apiService)

        runBlocking {
            val alumnos = apiService.getAlumnos()
            Log.d("@dev", "${alumnos.body()}")

            val alumnoId = apiService.getAlumno(2)
            Log.d("@dev", "${alumnoId.body()}")

            val deleteAlumno = apiService.deleteAlumno(1)
            Log.d("@dev", "Alumno borrado")
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