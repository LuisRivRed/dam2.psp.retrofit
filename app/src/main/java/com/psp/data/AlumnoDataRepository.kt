package com.psp.data

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.psp.model.Alumno
import com.psp.retrofit.LoginRequest
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class AlumnoDataRepository(private val alumnoService: AlumnoService) {

    suspend fun login(username: String, password:String): Result<String> = try {
        val response = alumnoService.login(LoginRequest(username, password))
        if (response.isSuccessful) {
            val token = response.body()?.token ?: throw Exception("Token no encontrado")
            RetrofitClient.setToken(token)
            Result.success(token)
        } else {
            Result.failure(Exception("Error en la autenticación"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getAlumnos(): Result<List<Alumno>> = try {
        val response = alumnoService.getAlumnos(token = "1")
        if (response.isSuccessful) {
            val alumnos = response.body() ?: emptyList()
            Result.success(alumnos)
        } else {
            Result.failure(Exception("Error en la respuesta del servidor"))
        }
        } catch (e: IOException) {
            Result.failure(e)
    }

    suspend fun getAlumnoByNombre(nombre: String) {
        executeApiCall {
            val alumno = alumnoService.getAlumnoNombre(nombre)
            Log.d("@dev", "Alumno por nombre ($nombre): $alumno")
        }
    }

    suspend fun getAlumnoByCurso(curso: String) {
        executeApiCall {
            val alumnos = alumnoService.getAlumnoCurso(curso)
            Log.d("@dev", "Alumnos por curso ($curso): $alumnos")
        }
    }

    suspend fun addAlumno(alumno: Alumno) {
        executeApiCall {
            alumnoService.addAlumno(alumno)
            Log.d("@dev", "Alumno añadido: $alumno")
        }
    }

    suspend fun deleteAlumno(id: Int) {
        executeApiCall {
            alumnoService.deleteAlumno(id)
            Log.d("@dev", "Alumno eliminado con id: $id")
        }
    }

    suspend fun getAlumnoById(id: Int) {
        executeApiCall {
            val alumno = alumnoService.getAlumnoId(id)
            Log.d("@dev", "Alumno por id ($id): $alumno")
        }
    }

    private suspend fun executeApiCall(action: suspend () -> Unit) {
        try {
            withContext(Dispatchers.IO) { action() }
        } catch (e: IOException) {
            Log.e("@dev", "Error de red: ${e.message}", e)
        } catch (e: Exception) {
            Log.e("@dev", "Error: ${e.message}", e)
        }
    }
}