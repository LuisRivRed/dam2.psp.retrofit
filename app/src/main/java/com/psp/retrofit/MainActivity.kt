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
import com.psp.data.remote.ApiClient
import com.psp.data.model.LoginRequest
import com.psp.retrofit.ui.theme.RetrofitTheme
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

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
        // Llamamos a la función que realiza el login y luego otras peticiones
        loginAndFetchData()
    }

    private fun loginAndFetchData() {
        lifecycleScope.launch {
            try {
                val loginResponse = ApiClient.apiService.login(LoginRequest("admin", "password"))
                if (loginResponse.isSuccessful) {
                    val token = loginResponse.body()?.token
                    if (token != null) {
                        Log.d("@dev", "Token recibido: $token")
                        ApiClient.setToken(token)

                        val alumnosResponse = ApiClient.apiService.getAlumnos()
                        if (alumnosResponse.isSuccessful) {
                            Log.d("@dev", "Alumnos: ${alumnosResponse.body()}")
                        } else {
                            Log.d("@dev", "Error al obtener alumnos: ${alumnosResponse.errorBody()?.string()}")
                        }
                    } else {
                        Log.d("@dev", "El token no se encontró en la respuesta.")
                    }
                } else {
                    Log.d("@dev", "Error en el login: ${loginResponse.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("@dev", "Excepción en el login: ${e.localizedMessage}", e)
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
