package com.psp.retrofit

import com.psp.data.ApiService
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import okhttp3.ResponseBody
import org.mockito.Mockito.`when`
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class StudentServiceTest {

    @Mock
    private lateinit var apiService: ApiService

    @Test
    fun getStudentsReturnsSuccessWithStudentsList() = runBlocking {

        val students = listOf(
            Alumno(
                id = 1,
                nombre = "Andrés Pérez",
                fechaNacimiento = "2002-07-15",
                curso = Curso.DAM1,
                email = "andres.perez@example.com",
                asignaturas = arrayListOf(Asignatura.DDI, Asignatura.AAD, Asignatura.PMDM)
            ),
            Alumno(
                id = 2,
                nombre = "Clara Méndez",
                fechaNacimiento = "2001-12-10",
                curso = Curso.DAM2,
                email = "clara.mendez@example.com",
                asignaturas = arrayListOf(Asignatura.SGE, Asignatura.PMDM, Asignatura.DDI)
            ),
            Alumno(
                id = 3,
                nombre = "Jorge Ramírez",
                fechaNacimiento = "2003-05-20",
                curso = Curso.DAW1,
                email = "jorge.ramirez@example.com",
                asignaturas = arrayListOf(Asignatura.AAD, Asignatura.PSP, Asignatura.DDI)
            ),
            Alumno(
                id = 4,
                nombre = "Natalia Suárez",
                fechaNacimiento = "2004-03-14",
                curso = Curso.DAW2,
                email = "natalia.suarez@example.com",
                asignaturas = arrayListOf(Asignatura.PMDM, Asignatura.PSP, Asignatura.SGE)
            ),
            Alumno(
                id = 5,
                nombre = "Luis Fernández",
                fechaNacimiento = "2004-08-22",
                curso = Curso.DAM2,
                email = "luis.fernandez@example.com",
                asignaturas = arrayListOf(Asignatura.AAD, Asignatura.PSP)
            ),
            Alumno(
                id = 6,
                nombre = "Ana López",
                fechaNacimiento = "2003-04-17",
                curso = Curso.DAW1,
                email = "sofia.lopez@example.com",
                asignaturas = arrayListOf(Asignatura.AAD)
            )
        )

        val mockResponse = Response.success(students)
        `when`(apiService.requestStudents()).thenReturn(mockResponse)
        val response = apiService.requestStudents()
        assertEquals(6, response.body()?.size)
    }

    @Test
    fun getStudentsReturnsFailure() = runBlocking {
        val errorResponse = Response.error<List<Alumno>>(500, ResponseBody.create(null, "Internal Server Error"))
        `when`(apiService.requestStudents()).thenReturn(errorResponse)
        val response = apiService.requestStudents()
        assertNull(response.body())
    }


    @Test
    fun addStudentReturnSuccessWithAddedStudent() = runBlocking {
        val newStudent = Alumno(
            id = 8,
            nombre = "Elisa Torres",
            fechaNacimiento = "2005-11-30",
            curso = Curso.DAM1,
            email = "elisa.torres@example.com",
            asignaturas = arrayListOf(Asignatura.AAD, Asignatura.PSP)
        )

        val mockResponse = Response.success(newStudent)
        `when`(apiService.addStudent(newStudent)).thenReturn(mockResponse)
        val response = apiService.addStudent(newStudent)
        assertEquals(newStudent, response.body())
    }

    @Test
    fun addStudentReturnFailure() = runBlocking {
        val newStudent = Alumno(
            id = 9,
            nombre = "Juan Herrera",
            fechaNacimiento = "2002-06-12",
            curso = Curso.DAW2,
            email = "juan.herrera@example.com",
            asignaturas = arrayListOf(Asignatura.AAD, Asignatura.SGE )
        )

        val errorResponse = Response.error<Alumno>(400, ResponseBody.create(null, "Invalid data"))
        `when`(apiService.addStudent(newStudent)).thenReturn(errorResponse)
        val response = apiService.addStudent(newStudent)
        assertNull(response.body())
    }

    @Test
    fun removeStudentByIdReturnsSuccessWithTrue() = runBlocking {
        val id = "2"
        val mockResponse = Response.success(true)
        `when`(apiService.deleteStudentById(id)).thenReturn(mockResponse)
        val response = apiService.deleteStudentById(id)
        assertEquals(response, mockResponse)
    }
}
