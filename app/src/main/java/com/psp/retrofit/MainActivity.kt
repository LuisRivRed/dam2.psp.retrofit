package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import com.psp.model.LoginRequest
import com.psp.retrofit.ui.theme.RetrofitTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val loginRequest = LoginRequest("admin", "password")
    val mainFactory = MainFactory()
    val loginUseCase = mainFactory.createLoginUseCase()
    val getAlumnosUseCase = mainFactory.createGetAlumnoUseCase()
    val getAlumnosByCursoUseCase = mainFactory.createGetAlumnoByCursoUseCase()
    val getAlumnosByNameUseCase = mainFactory.createGetAlumnoByNameUseCase()
    val saveAlumnoUseCase = mainFactory.createSaveAlumnoUseCase()
    val deleteAlumnoByIdUseCase = mainFactory.createDeleteAlumnoByIdUseCase()

    // Lógica para el login con LaunchedEffect y manejo de errores
    LaunchedEffect(Unit) {
        // Ejecutar operaciones en un hilo de secundario sin bloquear el hilo principal
        try {
            val result = loginUseCase.login(loginRequest)

            if (result.isSuccess) {
                val token = result.getOrNull()?.token
                Log.d("@dev", "Login Successful, Token: $token")

                // Realizar las demás llamadas y loguearlas de manera no bloqueante
                val alumnosResult = getAlumnosUseCase.invoke()
                Log.d("@dev", "Alumnos: $alumnosResult")

                val alumnosByCursoResult = getAlumnosByCursoUseCase.invoke(Curso.DAM2)
                Log.d("@dev", "Alumnos by curso: $alumnosByCursoResult")

                val alumnoByNameResult = getAlumnosByNameUseCase.invoke("Pepe")
                Log.d("@dev", "Alumno by name: $alumnoByNameResult")

                // Guardar un alumno nuevo y loguear el resultado
                val saveResult = saveAlumnoUseCase.invoke(
                    Alumno(
                        4, "Ian", "12/02/2001", Curso.DAM2,
                        "Ian@educa.jcyl.es", listOf(Asignatura.AAD, Asignatura.PSP)
                    )
                )
                if (saveResult.isSuccess) {
                    Log.d("@dev", "Alumno saved successfully")
                } else {
                    Log.d("@dev", "Failed to save alumno: ${saveResult.exceptionOrNull()?.message}")
                }

                // Borrar el alumno por ID y loguear el resultado
                val deleteResult = deleteAlumnoByIdUseCase.invoke(1)
                if (deleteResult.isSuccess) {
                    Log.d("@dev", "Alumno deleted successfully")
                } else {
                    Log.d(
                        "@dev",
                        "Failed to delete alumno: ${deleteResult.exceptionOrNull()?.message}"
                    )
                }

            } else {
                Log.d("@dev", "Login failed: ${result.exceptionOrNull()?.message}")
            }
        } catch (e: Exception) {
            Log.d("@dev", "Error: ${e.message}")
        }
    }
}

