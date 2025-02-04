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
import androidx.lifecycle.lifecycleScope
import com.psp.data.AlumnoApiModel
import com.psp.data.ApiClient
import com.psp.model.Asignatura
import com.psp.model.Curso
import com.psp.model.LoginRequest
import com.psp.model.TokenResponse
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    /**
     * ATENCIÓN: La información de los alumnos se muestra en el LogCat
     * con el tag @dev
     */

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
            }
        }
        loginAndFetchData()
        fetchAlumnoByCurso("DAM1")
        fetchAlumnoPorNombre("Pedro")
        addAlumno(
            alumno = AlumnoApiModel(
                "4",
                "Alejandro",
                "05/06/2001",
                Curso.DAM1,
                "alejandro@gmail.com",
                listOf(Asignatura.PSP, Asignatura.DDI, Asignatura.PMDM)
            )
        )
        deleteAlumno("3")

    }

    private fun fetchAlumnoByCurso(curso: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = ApiClient.apiService
                val response = apiService.getAlumno(curso.toString())


                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.d("@dev", "Alumno listado por curso: ${response.body()}")
                    } else {
                        Log.e("@dev", "Error: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("@dev", "Error al obtener alumnos: ${e.message}", e)
                }
            }
        }
    }

    //    private fun fetchAlumnos() {
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val apiService = ApiClient.apiService
//                val response = apiService.getAlumnos(token)
//
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful) {
//                        val alumnos = response.body()
//                        Log.d("@dev", "Alumnos obtenidos: ${alumnos?.toString()}")
//                    } else {
//                        Log.e("@dev", "Error al obtener alumnos: ${response.message()}")
//                    }
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    Log.e("@dev", "Error al obtener alumnos: ${e.message}", e)
//                }
//            }
//        }
//    }
    private fun loginAndFetchData() {
        lifecycleScope.launch {
            try {
                val loginResponse = ApiClient.apiService.login(LoginRequest("admin", "password"))
                if (loginResponse.isSuccessful) {
                    val token = loginResponse.body()?.token
                    if (token != null) {
                        Log.d("@dev", "Token recibido: $token")
                        ApiClient.setToken(token)

                        val alumnosResponse = ApiClient.apiService.getAlumnos(token)
                        if (alumnosResponse.isSuccessful) {
                            Log.d("@dev", "Alumnos: ${alumnosResponse.body()}")
                        } else {
                            Log.d(
                                "@dev",
                                "Error al obtener alumnos: ${alumnosResponse.errorBody()?.string()}"
                            )
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


private fun fetchAlumnoPorNombre(nombre: String) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val apiService = ApiClient.apiService
            val response = apiService.getAlumnoPorNombre(nombre)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val alumnos = response.body()
                    Log.d("@dev", "Alumnos con nombre $nombre: ${alumnos?.toString()}")
                } else {
                    Log.e("@dev", "Error al obtener alumnos por nombre: ${response.message()}")
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Log.e("@dev", "Error al obtener alumnos por nombre: ${e.message}", e)
            }
        }
    }
}

private fun addAlumno(alumno: AlumnoApiModel) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val apiService = ApiClient.apiService
            val response = apiService.addAlumno(alumno)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("@dev", "Alumno añadido: ${response.body()}")
                } else {
                    Log.e("@dev", "Error al añadir alumno: ${response.message()}")
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Log.e("@dev", "Error al añadir alumno: ${e.message}", e)
            }
        }
    }
}

private fun deleteAlumno(id: String) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val apiService = ApiClient.apiService
            val response = apiService.deleteAlumno(id)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("@dev", "Alumno eliminado")
                } else {
                    Log.e("@dev", "Error al eliminar alumno: ${response.message()}")
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Log.e("@dev", "Error al eliminar alumno: ${e.message}", e)
            }
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