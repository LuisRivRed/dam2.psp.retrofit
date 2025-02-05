package com.psp.retrofit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.psp.data.AlumnoDataRepository
import com.psp.data.AlumnoService
import com.psp.data.ApiClient
import com.psp.data.ApiError
import com.psp.data.RetrofitClient
import com.psp.model.Alumno
import com.psp.model.Curso
import com.psp.model.Asignatura
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val alumnoService: AlumnoService = ApiClient.apiService
    private val repository = AlumnoDataRepository(RetrofitClient.alumnoService)

    private fun loginUser() {
        lifecycleScope.launch {
            val result = repository.login("admin", "password")
            if (result.isSuccess) {
                val alumnos = repository.getAlumnos()
            } else {
                val exception = result.exceptionOrNull()
                exception?.printStackTrace()
            }
        }
    }

    private fun handleApiError(result: Result<*>) {
        result.onFailure { exception ->
            when(exception) {
                is IOException -> ApiError.NetworkError
                is HttpException -> ApiError.ServerError(exception.code(), exception.message())
                else -> ApiError.UnknownError(exception.localizedMessage ?: "Error desconocido")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        performAlumnoOperations()
    }



    private fun performAlumnoOperations() {
        val alumnoDataRepository = AlumnoDataRepository(alumnoService)

        lifecycleScope.launch {
            //alumnoDataRepository.getAlumnos()
            alumnoDataRepository.getAlumnoByNombre("Roberto")
            alumnoDataRepository.getAlumnoByCurso("DAM2")
            alumnoDataRepository.getAlumnoById(1)

            val newAlumno = Alumno(
                id = 4,
                nombre = "Arturo",
                fechaNacimiento = "5/06/2005",
                curso = Curso.DAM2,
                email = "arturo10@gmail.com",
                asignaturas = listOf(Asignatura.PSP, Asignatura.EIE, Asignatura.PMDM, Asignatura.AAD)
            )
            alumnoDataRepository.addAlumno(newAlumno)

            alumnoDataRepository.deleteAlumno(1)
        }
    }
}