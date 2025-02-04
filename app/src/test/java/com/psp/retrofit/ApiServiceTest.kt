package com.psp.retrofit

import com.psp.data.AlumnoApiDataSource
import com.psp.data.AlumnoService
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import com.psp.model.TokenResponse
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {
    @Mock
    private lateinit var alumnoService: AlumnoService
    private lateinit var alumnoApiDataSource : AlumnoApiDataSource

    @Before
    fun setup() {
        alumnoApiDataSource = AlumnoApiDataSource(alumnoService)
    }

    @Test
    fun loginSuccess() =
        runTest {
            val token = "test_token"
            whenever(alumnoService.login(any())).thenReturn(
                Response.success(TokenResponse(token))
            )

            val result = alumnoApiDataSource.login("admin", "password")

            assertTrue(result.isSuccess)
            assertEquals(token, result.getOrNull())
        }


    @Test
    fun getAlumnosLogin() =
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
            whenever(alumnoService.getAlumnos(any())).thenReturn(
                Response.success(mockAlumnos)
            )

            val result = alumnoApiDataSource.getAlumnosLogin()
            assertTrue(result.isSuccess)
            assertEquals(mockAlumnos, result.getOrNull())
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

            whenever(alumnoService.requestAlumnos()).thenReturn(
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

            whenever(alumnoService.requestAlumnosByCurso(curso)).thenReturn(
                Response.success(mockAlumnos)
            )

            val result = alumnoApiDataSource.getAlumnosByCurso(curso)
            assertTrue(result.isSuccessful)
            assertEquals(mockAlumnos, result.body())
        }


    @Test
    fun getAlumnosByCursoVacio() =
        runTest {
            val mockAlumnos = emptyList<Alumno>()
            val curso = "DAM2"

            whenever(alumnoService.requestAlumnosByCurso(curso)).thenReturn(
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

            whenever(alumnoService.requestAlumnoByNombre(nombre)).thenReturn(
                Response.success(mockAlumno)
            )

            val result = alumnoApiDataSource.getAlumnosByNombre(nombre)
            assertTrue(result.isSuccessful)
            assertEquals(mockAlumno, result.body())
        }

    @Test
    fun getAlumnosByNombreVacio() =
        runTest {
            val mockAlumno = null
            val nombre = "Alicia"

            whenever(alumnoService.requestAlumnoByNombre(nombre)).thenReturn(
                Response.success(mockAlumno)
            )

            val result = alumnoApiDataSource.getAlumnosByNombre(nombre)
            assertTrue(result.isSuccessful)
            assertEquals(mockAlumno, result.body())
        }

    @Test
    fun addAlumno() =
        runTest {
            val alumno = Alumno(5, "Javier", "27/08/2004", Curso.DAM2, "javier@educa.jcyl.es", listOf(Asignatura.DDI, Asignatura.AAD, Asignatura.PSP))

            whenever(alumnoService.addAlumno(alumno)).thenReturn(
                Response.success(Unit)
            )

            val result = alumnoApiDataSource.addAlumno(alumno)
            assertTrue(result.isSuccessful)
        }

    @Test
    fun deleteAlumno() =
        runTest {
            val id = 3
            whenever(alumnoService.deleteAlumnoById(id)).thenReturn(
                Response.success(Unit)
            )

            val result = alumnoApiDataSource.removeAlumno(id)
            assertTrue(result.isSuccessful)
        }
}