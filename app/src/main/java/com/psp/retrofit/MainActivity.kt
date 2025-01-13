package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.model.Alumno
import com.example.model.Curso
import com.example.model.Subject
import com.psp.retrofit.api.ApiClient
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

    }

    private fun getData() {
        lifecycleScope.launch {

            val service = ApiClient.studentService

            try {

                val students = service.getStudents()
                Log.d("@dev", "ALUMNOS: ${students.body()}")

                val studentsCurso = service.getByCourse("DAW1")
                Log.d("@dev", "CURSO : ${studentsCurso.body()}")

                val studentsNombre = service.getByName("Ana")
                Log.d("@dev", "NOMBRE : ${studentsNombre.body()}")

                val getById = "5"

                val studentsId = service.getById(getById)
                Log.d("@dev", "ID : ${studentsId.body()}")

                val alumno = Alumno(
                    id = 17,
                    name = "Ariana Grande",
                    birthDate = "1993-06-26",
                    curso = Curso.DAM2,
                    email = "ariana.grande@example.com",
                    subjects = arrayListOf(Subject.PMDM, Subject.AAD, Subject.PSP)
                )

                val addedStudent = service.addStudent(alumno)
                Log.d("@dev", "AÑADIDO : ${addedStudent.body()}")

                // Si que borra pero no consigo que muestre la respuesta bien
                val removeId = "1"

                service.removeById(removeId)
                val removedStudent = service.getById(removeId)
                Log.d("@dev", "BORRADO : ${removedStudent.body()}")


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }


}

