package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.CreateAlumnoUseCase
import com.psp.model.Curso
import com.psp.model.DeleteAlumnoUseCase
import com.psp.model.GetAlumnoByNombreUseCase
import com.psp.model.GetAlumnosByCursoUseCase
import com.psp.model.GetAlumnosUseCase
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
    }
}

fun retrofitGetAlumnos() {
    runBlocking {
        try {
            Log.d("@retrofit", "Lista de Alumnos:")
            val alumnos = GetAlumnosUseCase().invoke()
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
            val alumnosByCurso = GetAlumnosByCursoUseCase().invoke("DAM1")
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
            val alumnoByName = GetAlumnoByNombreUseCase().invoke("Kai")
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
            CreateAlumnoUseCase().invoke(alumno)
            Log.d("@retrofit", "Lista de alumnos despues de agregarle uno nuevo:")
            val alumnosNuevo = GetAlumnosUseCase().invoke()
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
            DeleteAlumnoUseCase().invoke(5)
            val alumnosDelete = GetAlumnosUseCase().invoke()
            for (a in alumnosDelete) {
                Log.d("@retrofit", "$a")
            }
        } catch (e: IOException) {
            Log.d("@retrofit", "Error de conexión: {$e}")
        }

    }
}
