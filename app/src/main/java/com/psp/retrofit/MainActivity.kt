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
import com.psp.model.Course
import com.psp.model.Student
import com.psp.model.Subject
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val apiService by lazy {
        ApiClient.provideRetrofit().create(com.psp.data.remote.ApiService::class.java)
    }

    private val studentRepository by lazy { StudentDataRepository(apiService) }

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
                    loginAndFetchStudents()
                }
            }
        }
    }

    private fun loginAndFetchStudents() {
        CoroutineScope(Dispatchers.IO).launch {
            val loginResponse = studentRepository.login("admin@educa.jcyl.es", "password")
            if (loginResponse.isSuccessful) {
                val token = loginResponse.body()?.token
                if (!token.isNullOrEmpty()) {
                    ApiClient.setToken(token) // Almacenar el token en el interceptor
                    Log.d("@dev", "Login exitoso, token: $token")

                    fetchStudents()
                    getStudentByName()
                    getStudentByCourse()
                    newStudent()
                    deleteStudent()

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
            val response = studentRepository.getStudents()
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

    private suspend fun getStudentByName() {
        val response = studentRepository.getStudentByName("Pedro")
        if (response.isSuccessful) {
            Log.d("@dev", "Estudiante encontrado por nombre: ${response.body()}")
        } else {
            Log.d("@dev", "Error al obtener estudiante por nombre: ${response.code()}")
        }
    }

    private suspend fun getStudentByCourse() {
        val response = studentRepository.getStudentByCourse(Course.DAM1)
        if (response.isSuccessful) {
            Log.d("@dev", "Estudiantes encontrados en el curso: ${response.body()}")
        } else {
            Log.d("@dev", "Error al obtener estudiantes por curso: ${response.code()}")
        }
    }

    private suspend fun newStudent() {
        val newStudent = Student(
            id = 0,
            name = "Maria",
            dateBirth = "10/10/2000",
            course = Course.DAW1,
            email = "maria@educa.jcyl.es",
            subject = listOf(Subject.PSP, Subject.AAD)
        )
        val response = studentRepository.newStudent(newStudent)
        if (response.isSuccessful) {
            Log.d("@dev", "Nuevo estudiante agregado: ${response.body()}")
        } else {
            Log.d("@dev", "Error al agregar nuevo estudiante: ${response.code()}")
        }
    }

    private suspend fun deleteStudent() {
        val response = studentRepository.deleteStudent(3)
        if (response.isSuccessful) {
            Log.d("@dev", "Estudiante eliminado correctamente")
        } else {
            Log.d("@dev", "Error al eliminar estudiante: ${response.code()}")
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