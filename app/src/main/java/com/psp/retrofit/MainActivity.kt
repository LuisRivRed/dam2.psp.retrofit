package com.psp.retrofit

import android.os.Bundle
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
import com.psp.data.ApiClient
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RetrofitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(name = "Android", modifier = Modifier.padding(innerPadding))
                }
            }
        }

        // Perform API calls
        performApiCalls()
    }
}

// Function to perform API calls
fun performApiCalls() {
    val apiService = ApiClient.apiService

    runBlocking {
        println("API Responses: Students\n")

        // Get all students
        val students = apiService.getAlumnos()
        println("All students:\n$students")

        // Get student by name
        val studentByName = apiService.getAlumnoNombre("Alice")
        println("\nStudent by name: $studentByName")

        // Get student by ID
        val studentById = apiService.getAlumnoById(4)  // Example ID
        println("\nStudent by ID: $studentById")

        // Get students by course
        val studentsByCourse = apiService.getAlumnoCurso("DAM2")
        println("\nStudents by course: $studentsByCourse")

        // Get students by subject
        val studentsBySubject = apiService.getAlumnoAsignatura("PSP")
        println("\nStudents by subject: $studentsBySubject")

        // Add multiple new students
        val newStudents = listOf(
            Alumno(
                id = 4,
                nombre = "Mark",
                fechaNacimiento = "14/06/2003",
                curso = Curso.DAW1,
                email = "mark12@gmail.com",
                asignaturas = listOf(Asignatura.PSP, Asignatura.EIE, Asignatura.PMDM)
            ),
            Alumno(
                id = 5,
                nombre = "John",
                fechaNacimiento = "12/02/2002",
                curso = Curso.DAW1,
                email = "john24@gmail.com",
                asignaturas = listOf(Asignatura.PSP, Asignatura.PMDM)
            ),
            Alumno(
                id = 6,
                nombre = "Emma",
                fechaNacimiento = "03/11/2001",
                curso = Curso.DAW2,
                email = "emma.smith@gmail.com",
                asignaturas = listOf(Asignatura.EIE, Asignatura.PSP)
            )
        )

        // Add the students
        newStudents.forEach { student ->
            apiService.addAlumno(student)
        }

        // Print students after addition
        println("\nStudents after addition:\n${apiService.getAlumnos()}")

        // Update a student by ID
        val updatedStudent = Alumno(
            id = 4,
            nombre = "Mark Updated",
            fechaNacimiento = "14/06/2003",
            curso = Curso.DAW2,
            email = "mark.updated@gmail.com",
            asignaturas = listOf(Asignatura.PSP, Asignatura.EIE, Asignatura.PMDM)
        )
        apiService.updateAlumno(4, updatedStudent)

        // Print students after update
        println("\nStudents after update:\n${apiService.getAlumnos()}")

        // Partially update a student by ID
        val partialUpdate = Alumno(
            id = 4,
            nombre = "Mark Partial Updated",
            fechaNacimiento = "14/06/2003",
            curso = Curso.DAW2,
            email = "mark.partial.updated@gmail.com",
            asignaturas = listOf(Asignatura.PSP, Asignatura.EIE, Asignatura.PMDM)
        )
        apiService.patchAlumno(4, partialUpdate)

        // Print students after partial update
        println("\nStudents after partial update:\n${apiService.getAlumnos()}")

        // Delete a student by ID
        apiService.deleteAlumno(1)

        // Print students after deletion
        println("\nStudents after deletion:\n${apiService.getAlumnos()}")
    }
}

// Composable function to display a greeting message
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

// Preview function for the Greeting Composable
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitTheme {
        Greeting("Android")
    }
}
