package com.psp.retrofit.model


import com.psp.data.AlumnoDataRepository
import com.psp.data.remote.ApiService
import com.psp.data.model.Alumno
import com.psp.data.model.Asignatura
import com.psp.data.model.Curso
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AlumnoServiceTest {

    @Mock
    private lateinit var apiService: ApiService
    private lateinit var data: AlumnoDataRepository

    val mockAlumnos = listOf(
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
            email = "alejandro",
            nombre = "alejandro@gmail.com",
            asignaturas = listOf(Asignatura.AAD, Asignatura.PMDM, Asignatura.PSP),
            fechaNacimiento = "453543543"
        ),
        Alumno(
            id = 3,
            curso = Curso.DAM2,
            email = "juanda",
            nombre = "juanda@gmail.com",
            asignaturas = listOf(Asignatura.AAD, Asignatura.PMDM, Asignatura.PSP),
            fechaNacimiento = "1443543543"
        ),
    )

    @Before
    fun setup() {
        data = AlumnoDataRepository(apiService)
    }

    @Test
    fun getAlumnos() = runTest {
        whenever(apiService.getAlumnos()).thenReturn(Response.success(mockAlumnos))

        val response = data.getAlumnos()

        Assert.assertTrue(response.isSuccessful)

        Assert.assertEquals(mockAlumnos, response.body())
    }

    @Test
    fun getAlumnosNull() = runTest {
        whenever(apiService.getAlumnos()).thenReturn(
            Response.error(404, okhttp3.ResponseBody.create(null, "Not Found"))
        )

        val response = data.getAlumnos()

        Assert.assertFalse(response.isSuccessful)

        Assert.assertNull(response.body())
    }

    @Test
    fun getAlumnoById() = runTest{
        val id = 1

        whenever(apiService.getAlumno(id)).thenReturn(Response.success(mockAlumnos[1]))

        val response = data.getAlumno(id)

        Assert.assertTrue(response.isSuccessful)
        Assert.assertEquals(mockAlumnos[id], response.body())
    }

    @Test
    fun deleteAlumno() = runTest {
        val id = 2

        whenever(apiService.deleteAlumno(id)).thenReturn(Response.success(Unit))

        val response = data.deleteAlumno(id)

        Assert.assertTrue(response.isSuccessful)
        Assert.assertEquals(Unit, response.body())
    }

    @Test
    fun getAlumnosEmpty() = runTest {
        whenever(apiService.getAlumnos()).thenReturn(Response.success(emptyList()))

        val response = data.getAlumnos()

        Assert.assertTrue(response.isSuccessful)
        Assert.assertTrue(response.body().isNullOrEmpty())
    }

    @Test
    fun getAlumnosByIdNotFound() = runTest {
        val id = 99

        whenever(apiService.getAlumno(id)).thenReturn(Response.error(404, okhttp3.ResponseBody.create(null, "Not Found")))

        val response = data.getAlumno(id)

        Assert.assertFalse(response.isSuccessful)
        Assert.assertNull(response.body())
    }

    @Test
    fun deleteAlumnoNotFound() = runTest {
        val id = 99

        whenever(apiService.deleteAlumno(id)).thenReturn(
            Response.error(500, okhttp3.ResponseBody.create(null, "Internal Server Error"))
        )

        val response = data.deleteAlumno(id)

        Assert.assertFalse(response.isSuccessful)
        Assert.assertEquals(500, response.body())
    }
}

