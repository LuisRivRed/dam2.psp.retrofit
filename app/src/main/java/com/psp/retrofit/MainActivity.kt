package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.psp.model.AlumnoRepository
import com.psp.data.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val repository = AlumnoRepository(RetrofitClient.apiService)
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginUser()
    }

    private fun loginUser() {
        lifecycleScope.launch {
            val result = repository.login("admin", "password")
            if (result.isSuccess) {
                token = result.getOrNull()
                Log.d("@dev", "Login exitoso, token: $token")
                loadAlumnos()
            } else {
                Log.e("@dev", "Error de login: ${result.exceptionOrNull()?.message}")
            }
        }
    }

    private fun loadAlumnos() {
        lifecycleScope.launch {
            token?.let {
                val result = repository.getAlumnos(it)
                if (result.isSuccess) {
                    Log.d("@dev", "Alumnos recibidos: ${result.getOrNull()?.size}")
                } else {
                    Log.e("@dev", "Error al obtener alumnos: ${result.exceptionOrNull()?.message}")
                }
            } ?: Log.e("@dev", "Token no disponible, inicia sesión primero.")
        }
    }
}