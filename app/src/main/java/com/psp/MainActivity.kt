package com.psp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.psp.data.ApiClient
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import kotlinx.coroutines.runBlocking


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        }
        main()
    }

    fun main() {
        val apiClient = ApiClient()
        val apiService = apiClient.apiService
        runBlocking {
            val response1 = apiService.requestStudents()
            println(response1.body())
            val response2 = apiService.requestStudentByName("Carlos")
            println(response2.body())
            val response3 = apiService.requestStudentsByCourse("DAM2")
            println(response3.body())

            val student = Alumno(2, "Henar", "2003-06-15", Curso.DAM2, "henarhh14@gmail.com", listOf(Asignatura.AAD, Asignatura.DDI, Asignatura.PMDM))
            apiService.addStudent(student)
            val response4 = apiService.requestStudents()
            println(response4.body())
            val response5 = apiService.deleteStudentById("1")
            println(response5.toString())
            val response6 = apiService.requestStudents()
            println(response6.body())
        }
    }
}
