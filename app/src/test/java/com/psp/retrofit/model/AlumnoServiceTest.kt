package com.psp.retrofit.model

import com.psp.data.model.Alumno
import com.psp.data.model.Asignatura
import com.psp.data.model.Curso
import com.psp.data.model.LoginRequest
import com.psp.data.model.TokenResponse
import com.psp.data.remote.ApiService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AlumnoServiceTest {

    @Mock
    private lateinit var apiService: ApiService

    private var tokenPrueba = "test_token"

    private val mockAlumnos = listOf(
        Alumno(
            id = 1,
            curso = Curso.DAM1,
            email = "enrique@gmail.com",
            nombre = "enrique",
            asignaturas = listOf(Asignatura.AAD, Asignatura.PMDM),
            fechaNacimiento = "12121"
        ),
        Alumno(
            id = 2,
            curso = Curso.DAM2,
            email = "alejandro@gmail.com",
            nombre = "alejandro",
            asignaturas = listOf(Asignatura.AAD, Asignatura.PMDM, Asignatura.PSP),
            fechaNacimiento = "453543543"
        ),
        Alumno(
            id = 3,
            curso = Curso.DAM2,
            email = "juanda@gmail.com",
            nombre = "juanda",
            asignaturas = listOf(Asignatura.AAD, Asignatura.PMDM, Asignatura.PSP),
            fechaNacimiento = "1443543543"
        )
    )

    @Before
    fun setup() {
    }

    @Test
    fun getAlumnos() = runTest {
        whenever(apiService.getAlumnos(tokenPrueba)).thenReturn(Response.success(mockAlumnos))

        val response = apiService.getAlumnos(tokenPrueba)

        Assert.assertTrue(response.isSuccessful)
        assertEquals(mockAlumnos, response.body())
    }

    @Test
    fun getAlumnosNull() = runTest {
        val errorResponseBody = ResponseBody.create("application/json".toMediaTypeOrNull(), "Not Found")
        whenever(apiService.getAlumnos(tokenPrueba)).thenReturn(Response.error(404, errorResponseBody))

        val response = apiService.getAlumnos(tokenPrueba)

        Assert.assertFalse(response.isSuccessful)
        Assert.assertNull(response.body())
    }

    @Test
    fun getAlumnoById() = runTest {
        val id = 1
        whenever(apiService.getAlumno(id)).thenReturn(Response.success(mockAlumnos[1]))

        val response = apiService.getAlumno(id)

        Assert.assertTrue(response.isSuccessful)
        assertEquals(mockAlumnos[1], response.body())
    }

    @Test
    fun deleteAlumno() = runTest {
        val id = 2
        whenever(apiService.deleteAlumno(id)).thenReturn(Response.success(Unit))

        val response = apiService.deleteAlumno(id)

        Assert.assertTrue(response.isSuccessful)
        assertEquals(Unit, response.body())
    }

    @Test
    fun getAlumnosEmpty() = runTest {
        whenever(apiService.getAlumnos(tokenPrueba)).thenReturn(Response.success(emptyList()))

        val response = apiService.getAlumnos(tokenPrueba)

        Assert.assertTrue(response.isSuccessful)
        Assert.assertTrue(response.body().isNullOrEmpty())
    }

    @Test
    fun getAlumnoByIdNotFound() = runTest {
        val id = 99
        val errorResponseBody = ResponseBody.create("application/json".toMediaTypeOrNull(), "Not Found")
        whenever(apiService.getAlumno(id)).thenReturn(Response.error(404, errorResponseBody))

        val response = apiService.getAlumno(id)

        Assert.assertFalse(response.isSuccessful)
        Assert.assertNull(response.body())
    }

    @Test
    fun deleteAlumnoNotFound() = runTest {
        val id = 99
        val errorResponseBody = ResponseBody.create("application/json".toMediaTypeOrNull(), "Internal Server Error")
        whenever(apiService.deleteAlumno(id)).thenReturn(Response.error(500, errorResponseBody))

        val response = apiService.deleteAlumno(id)

        Assert.assertFalse(response.isSuccessful)
        Assert.assertNull(response.body())
    }

    @Test
    fun `login success returns token`() = runTest {
        val token = "test_token"
        whenever(apiService.login(any())).thenReturn(Response.success(TokenResponse(token)))

        val response = apiService.login(LoginRequest("admin", "password"))

        assertTrue(response.isSuccessful)
        assertEquals(token, response.body()?.token)
    }
}
