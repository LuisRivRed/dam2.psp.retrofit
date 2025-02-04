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
import com.psp.data.model.AlumnoRepository
import com.psp.retrofit.ui.theme.RetrofitTheme
import androidx.lifecycle.lifecycleScope
import com.psp.data.remote.ApiClient
import com.psp.data.remote.ApiService
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Enrique Arenas", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        deleteAlumno(1)
        getAlumno("3")
        loginAndFetchData()
    }

    private fun loginAndFetchData() {
        lifecycleScope.launch {
                val result = AlumnoRepository.login("admin", "password")
                result.onSuccess { token ->
                    Log.d("@dev", "Token recibido: $token")

                    val alumnosResponse = AlumnoRepository.getAlumnos(token)
                    alumnosResponse.onSuccess { alumnos ->
                        Log.d("@dev", "Alumnos: $alumnos")
                    }.onFailure { e ->
                        Log.e("@dev", "Error al obtener alumnos: ${e.localizedMessage}", e)
                    }
                }.onFailure { e ->
                    Log.e("@dev", "Error en el login: ${e.localizedMessage}", e)
                }
        }
    }

    private fun deleteAlumno(id: Int) {
        lifecycleScope.launch {
                val result = ApiClient.apiService.deleteAlumno(id)
                if (result.isSuccessful) {
                    Log.d("@dev", "Alumno eliminado correctamente")
                } else {
                    Log.d("@dev", "Error al eliminar alumno: ${result.errorBody()?.string()}")
                }
        }
    }

    private fun getAlumno(id : String){
        lifecycleScope.launch {
                val result = ApiClient.apiService.getAlumno(id.toInt())
                if (result.isSuccessful) {
                    Log.d("@dev", "Alumno con id: $id ${result.body()}")
                } else {
                    Log.d("@dev", "Error al obtener alumno: ${result.errorBody()?.string()}")
                }
        }
    }
}

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "PSP $name!", modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        RetrofitTheme {
            Greeting("Enrique Arenas")
        }
    }
