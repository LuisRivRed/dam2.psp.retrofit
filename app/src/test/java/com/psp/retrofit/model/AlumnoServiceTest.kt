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
            fechaNacimiento = "12121234434"
        )
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
    fun nullAlumnos() = runTest {
        whenever(apiService.getAlumnos()).thenReturn(
            Response.error(404, okhttp3.ResponseBody.create(null, "Not Found"))
        )

        val response = data.getAlumnos()

        Assert.assertFalse(response.isSuccessful)

        Assert.assertNull(response.body())
    }
}

