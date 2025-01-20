package com.psp.retrofit

import com.psp.data.AlumnoApiDataSource
import com.psp.data.AlumnoService
import com.psp.data.ApiClient
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {
    @Mock
    private lateinit var alumnoService: AlumnoService
    private lateinit var alumnoApiDataSource : AlumnoApiDataSource

    @Before
    fun setup() {
        alumnoService = ApiClient.provideAlumnoService()
        alumnoApiDataSource = Mockito.mock(AlumnoApiDataSource(alumnoService).javaClass)
    }

    @Test
    fun getAlumnosBueno() =
        runTest {
            val mockAlumnos = listOf(
                Alumno(1, "Pedro", "05/06/2000", Curso.DAM1,
                    "pedro@educa.jcyl.es", listOf(Asignatura.AAD, Asignatura.PMDM, Asignatura.EIE)
                ),
                Alumno(2, "Pepe", "12/02/2001", Curso.DAM2,
                    "pepe@educa.jcyl.es", listOf(Asignatura.AAD, Asignatura.PSP)
                ),
                Alumno(3, "Alicia", "05/06/2000", Curso.DAW1,
                    "alicia@educa.jcyl.es", listOf(Asignatura.DDI, Asignatura.AAD, Asignatura.PSP)
                )
            )

            whenever(alumnoApiDataSource.getAlumnos()).thenReturn(
                Response.success(mockAlumnos)
            )

            val result = alumnoApiDataSource.getAlumnos()
            assertTrue(result.isSuccessful)
            assertEquals(mockAlumnos, result.body())
        }

    @Test
    fun getAlumnosByCursoBueno() =
        runTest {
            val mockAlumnos = listOf(
                Alumno(2, "Pepe", "12/02/2001", Curso.DAM2,
                    "pepe@educa.jcyl.es", listOf(Asignatura.AAD, Asignatura.PSP))
            )
            val curso = "DAM2"

            whenever(alumnoApiDataSource.getAlumnosByCurso(curso)).thenReturn(
                Response.success(mockAlumnos)
            )

            val result = alumnoApiDataSource.getAlumnosByCurso(curso)
            assertTrue(result.isSuccessful)
            assertEquals(mockAlumnos, result.body())
        }

    @Test
    fun getAlumnoByNombreBueno() =
        runTest {
            val mockAlumno = Alumno(3, "Alicia", "05/06/2000", Curso.DAW1, "alicia@educa.jcyl.es", listOf(Asignatura.DDI, Asignatura.AAD, Asignatura.PSP))

            val nombre = "Alicia"

            whenever(alumnoApiDataSource.getAlumnosByNombre(nombre)).thenReturn(
                Response.success(mockAlumno)
            )

            val result = alumnoApiDataSource.getAlumnosByNombre(nombre)
            assertTrue(result.isSuccessful)
            assertEquals(mockAlumno, result.body())
        }
}