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
                    fetchStudents()
                }
            }
        }
    }

    private fun fetchStudents() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = alumnoRepository.getStudents()
            if (response.isSuccessful) {
                val students = response.body()
                students?.forEach {
                    Log.d("@dev","$it")
                }
            } else {
                println("Error: ${response.code()}")
            }
        }
    }
}

