package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import com.psp.data.StudentDataRepository
import com.psp.data.remote.ApiClient
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val apiService by lazy {
        ApiClient.provideRetrofit().create(com.psp.data.remote.ApiService::class.java)
    }

    private val alumnoRepository by lazy { StudentDataRepository(apiService) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitTheme {
                LaunchedEffect(Unit) {
                    loginAndFetchStudents()
                }
            }
        }
    }

    private fun loginAndFetchStudents() {
        CoroutineScope(Dispatchers.IO).launch {
            val loginResponse = alumnoRepository.login("pedro@educa.jcyl.es", "password")
            if (loginResponse.isSuccessful) {
                val token = loginResponse.body()?.token
                if (!token.isNullOrEmpty()) {
                    ApiClient.setToken(token)
                    Log.d("@dev", "Login exitoso, token: $token")
                    fetchStudents()
                } else {
                    Log.d("@dev", "Error: el token recibido es nulo o vacío")
                }
            } else {
                Log.d("@dev", "Error de login: ${loginResponse.code()}")
            }
        }
    }

    private fun fetchStudents() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = alumnoRepository.getStudents()
            if (response.isSuccessful) {
                val students = response.body()
                students?.forEach {
                    Log.d("@dev", it.toString())
                }
            } else {
                Log.d("@dev", "Error al obtener estudiantes: ${response.code()}")
            }
        }
    }
}

