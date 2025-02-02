package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.psp.data.AlumnoDataRepository
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.CreateAlumnoUseCase
import com.psp.model.Curso
import com.psp.model.DeleteAlumnoUseCase
import com.psp.model.GetAlumnoByNombreUseCase
import com.psp.model.GetAlumnosByCursoUseCase
import com.psp.model.GetAlumnosUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {}

        retrofitGetAlumnos()
        retrofitGetAlumnosByCurso()
        retrofitGetAlumnoByNombre()
        retrofitSaveAlumno()
        retrofitDeleteAlumnos()

        loginUser()
    }


    private fun loginUser() {
        lifecycleScope.launch {
            val result = AlumnoDataRepository.login("admin", "password")
            if (result.isSuccess) {
                val alumnos = AlumnoDataRepository.getAlumnos()

            } else {
                // Manejar error
            }
        }
    }
}


fun retrofitGetAlumnos() {
    runBlocking {
        try {
            Log.d("@retrofit", "Lista de Alumnos:")
            val alumnos = GetAlumnosUseCase(AlumnoDataRepository).invoke()
            for (alumno in alumnos) {
                Log.d("@retrofit", "$alumno")
            }
        } catch (e: IOException) {
            Log.d("@retrofit", "Error de conexión: {$e}")
        }

    }
}

fun retrofitGetAlumnosByCurso() {
    runBlocking {
        try {
            Log.d("@retrofit", "Lista de Alumnos filtrados por curso:")
            val alumnosByCurso = GetAlumnosByCursoUseCase(AlumnoDataRepository).invoke("DAM1")
            for (alumno in alumnosByCurso) {
                Log.d("@retrofit", "$alumno")
            }
        } catch (e: IOException) {
            Log.d("@retrofit", "Error de conexión: {$e}")
        }

    }
}

fun retrofitGetAlumnoByNombre() {
    runBlocking {
        try {
            Log.d("@retrofit", "Datos de un Alumno recogido por su nombre:")
            val alumnoByName = GetAlumnoByNombreUseCase(AlumnoDataRepository).invoke("Kai")
            Log.d("@retrofit", "$alumnoByName")
        } catch (e: IOException) {
            Log.d("@retrofit", "Error de conexión: {$e}")
        }

    }
}

fun retrofitSaveAlumno() {
    runBlocking {
        try {
            val alumno = Alumno(
                5, "Ian", "2004/05/10", Curso.DAM2, "ian@example.com",
                listOf(Asignatura.PSP, Asignatura.SGE, Asignatura.AAD)
            )
            CreateAlumnoUseCase(AlumnoDataRepository).invoke(alumno)
            Log.d("@retrofit", "Lista de alumnos despues de agregarle uno nuevo:")
            val alumnosNuevo = GetAlumnosUseCase(AlumnoDataRepository).invoke()
            for (a in alumnosNuevo) {
                Log.d("@retrofit", "$a")
            }
        } catch (e: IOException) {
            Log.d("@retrofit", "Error de conexión: {$e}")
        }

    }
}

fun retrofitDeleteAlumnos() {
    runBlocking {
        try {
            Log.d("@retrofit", "Lista de alumnos despues de borrarle el que agregamos antes:")
            DeleteAlumnoUseCase(AlumnoDataRepository).invoke(5)
            val alumnosDelete = GetAlumnosUseCase(AlumnoDataRepository).invoke()
            for (a in alumnosDelete) {
                Log.d("@retrofit", "$a")
            }
        } catch (e: IOException) {
            Log.d("@retrofit", "Error de conexión: {$e}")
        }

    }
}


