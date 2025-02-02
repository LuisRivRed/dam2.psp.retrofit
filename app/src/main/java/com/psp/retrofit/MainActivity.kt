package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.psp.domain.model.Alumno
import com.psp.domain.model.Curso
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import com.psp.data.remote.ApiClient
import com.psp.domain.LoginRequest


class MainActivity : ComponentActivity() {

    private val alumnosService = AlumnosService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val result = login("admin", "password")
            if (result.isSuccess) {
                val token = result.getOrNull()
                ApiClient.setToken(token!!)
                Log.d("Auth", "Token obtenido: $token")

                getAlumnos()
                getAlumnosByCurso()
                createAlumno()
                getAlumnoByNombre()
                deleteAlumno()
            } else {
                Log.e("Auth", "Error en la autenticación")
            }
        }
    }

    suspend fun login(username: String, password: String): Result<String> {
        return try {
            Log.d("Auth", "Iniciando sesión con $username")
            val response = AlumnosService.login(LoginRequest(username, password))

            Log.d("Auth", "Código de respuesta: ${response.code()}")
            Log.d("Auth", "Cuerpo de respuesta: ${response.errorBody()?.string()}")

            if (response.isSuccessful) {
                val token = response.body()?.token ?: throw Exception("Token no encontrado")
                Log.d("Auth", "Token recibido: $token")
                Result.success(token)
            } else {
                Result.failure(Exception("Error de autenticación: ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.e("Auth", "Excepción en login: ${e.message}")
            Result.failure(e)
        }
    }

    fun getAlumnos() {
        lifecycleScope.launch(Dispatchers.IO) {
            safeApiCall("getAlumnos") { alumnosService.getAlumnos() }
        }
    }

    fun getAlumnosByCurso() {
        lifecycleScope.launch(Dispatchers.IO) {
            safeApiCall("getAlumnosByCurso") { alumnosService.getAlumnosByCurso("DAM1") }
        }
    }

    fun createAlumno() {
        val alumno = Alumno(
            id = 0,
            nombre = "Rubén",
            fechaNacimiento = "01/01/1999",
            curso = Curso.DAM1,
            email = "william.henry.harrison@example-pet-store.com",
            asignatura = emptyList()
        )
        lifecycleScope.launch(Dispatchers.IO) {
            safeApiCall("createAlumno") { alumnosService.createAlumno(alumno) }
        }
    }

    fun getAlumnoByNombre() {
        lifecycleScope.launch(Dispatchers.IO) {
            safeApiCall("getAlumnoByNombre") { alumnosService.getAlumnoByNombre("Rubén") }
        }
    }

    fun deleteAlumno() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val id = 1
                alumnosService.deleteAlumno(id)
                Log.d("API Response", "Alumno con ID $id eliminado correctamente")
            } catch (e: HttpException) {
                Log.e("API Error", "HTTP ${e.code()} - ${e.message()}")
            } catch (e: Exception) {
                Log.e("API Exception", e.message ?: "Error desconocido")
            }
        }
    }

    private suspend fun <T> safeApiCall(tag: String, apiCall: suspend () -> Response<T>) {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                Log.d("API Response - $tag", response.body().toString())
            } else {
                Log.e(
                    "API Error - $tag",
                    "HTTP ${response.code()} - ${response.errorBody()?.string()}"
                )
            }
        } catch (e: HttpException) {
            Log.e("API Error - $tag", "HTTP ${e.code()} - ${e.message()}")
        } catch (e: Exception) {
            Log.e("API Exception - $tag", e.message ?: "Error desconocido")
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