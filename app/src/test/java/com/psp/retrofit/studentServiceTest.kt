package com.psp.retrofit

import com.psp.data.ApiService
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import com.psp.model.LoginRequest
import com.psp.model.TokenResponse
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
    fun loginReturnsSuccessWithToken() = runBlocking {
        val loginRequest = LoginRequest("henar@henar", "password")
        val tokenResponse = TokenResponse("mock_token")

        val mockResponse = Response.success(tokenResponse)
        `when`(apiService.login(loginRequest)).thenReturn(mockResponse)

        val response = apiService.login(loginRequest)
        assertEquals("mock_token", response.body()?.token)
    }

    @Test
    fun loginReturnsFailure() = runBlocking {
        val loginRequest = LoginRequest("henar@henar", "wrongpassword")
        val errorResponse = Response.error<TokenResponse>(401, ResponseBody.create(null, "Unauthorized"))

        `when`(apiService.login(loginRequest)).thenReturn(errorResponse)

        val response = apiService.login(loginRequest)
        assertNull(response.body())
    }

    @Test
    fun getStudentsReturnsSuccessWithStudentsList() = runBlocking {
        val students = listOf(
            Alumno(
                id = 1,
                nombre = "Andrés Pérez",
                fechaNacimiento = "2002-07-15",
                curso = Curso.DAM1,
                email = "andres.perez@example.com",
                asignaturas = listOf(Asignatura.AAD, Asignatura.PSP)
            ),
            Alumno(
                id = 2,
                nombre = "Clara Méndez",
                fechaNacimiento = "2001-12-10",
                curso = Curso.DAM2,
                email = "clara.mendez@example.com",
                asignaturas = listOf(Asignatura.AAD, Asignatura.PSP)
            )
        )

        val mockResponse = Response.success(students)
        `when`(apiService.getAlumnos("mock_token")).thenReturn(mockResponse)
        val response = apiService.getAlumnos("mock_token")
        assertEquals(2, response.body()?.size)
    }

    @Test
    fun getStudentsReturnsFailure() = runBlocking {
        val errorResponse = Response.error<List<Alumno>>(500, ResponseBody.create(null, "Internal Server Error"))
        `when`(apiService.getAlumnos("mock_token")).thenReturn(errorResponse)
        val response = apiService.getAlumnos("mock_token")
        assertNull(response.body())
    }

    @Test
    fun getAlumnoByIdReturnsSuccess() = runBlocking {
        val student = Alumno(
            id = 1,
            nombre = "Andrés Pérez",
            fechaNacimiento = "2002-07-15",
            curso = Curso.DAM1,
            email = "andres.perez@example.com",
            asignaturas = listOf(Asignatura.AAD, Asignatura.PSP)
        )

        val mockResponse = Response.success(student)
        `when`(apiService.getAlumnoById("mock_token", 1)).thenReturn(mockResponse)
        val response = apiService.getAlumnoById("mock_token", 1)
        assertEquals(student, response.body())
    }

    @Test
    fun getAlumnoByIdReturnsFailure() = runBlocking {
        val errorResponse = Response.error<Alumno>(404, ResponseBody.create(null, "Not Found"))
        `when`(apiService.getAlumnoById("mock_token", 999)).thenReturn(errorResponse)
        val response = apiService.getAlumnoById("mock_token", 999)
        assertNull(response.body())
    }

    @Test
    fun getAlumnosByCursoReturnsSuccess() = runBlocking {
        val studentsInCourse = listOf(
            Alumno(
                id = 1,
                nombre = "Andrés Pérez",
                fechaNacimiento = "2002-07-15",
                curso = Curso.DAM1,
                email = "andres.perez@example.com",
                asignaturas = listOf(Asignatura.AAD, Asignatura.PSP)

            )
        )

        val mockResponse = Response.success(studentsInCourse)
        `when`(apiService.getAlumnosByCurso("mock_token", "DAM1")).thenReturn(mockResponse)
        val response = apiService.getAlumnosByCurso("mock_token", "DAM1")
        assertEquals(1, response.body()?.size)
    }

    @Test
    fun getAlumnosByCursoReturnsFailure() = runBlocking {
        val errorResponse = Response.error<List<Alumno>>(500, ResponseBody.create(null, "Internal Server Error"))
        `when`(apiService.getAlumnosByCurso("mock_token", "DAM1")).thenReturn(errorResponse)
        val response = apiService.getAlumnosByCurso("mock_token", "DAM1")
        assertNull(response.body())
    }

    @Test
    fun addStudentReturnsSuccessWithAddedStudent() = runBlocking {
        val newStudent = Alumno(
            id = 8,
            nombre = "Elisa Torres",
            fechaNacimiento = "2005-11-30",
            curso = Curso.DAM1,
            email = "elisa.torres@example.com",
            asignaturas = listOf(Asignatura.AAD, Asignatura.PSP)
        )

        val mockResponse = Response.success(newStudent)
        `when`(apiService.addStudent(newStudent)).thenReturn(mockResponse)
        val response = apiService.addStudent(newStudent)
        assertEquals(newStudent, response.body())
    }

    @Test
    fun addStudentReturnsFailure() = runBlocking {
        val newStudent = Alumno(
            id = 9,
            nombre = "Juan Herrera",
            fechaNacimiento = "2002-06-12",
            curso = Curso.DAW2,
            email = "juan.herrera@example.com",
            asignaturas = listOf(Asignatura.AAD, Asignatura.PSP)
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
