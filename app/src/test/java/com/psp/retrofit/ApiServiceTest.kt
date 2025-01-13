package com.psp.retrofit

import com.psp.kotlin.AlumnoRepository
import com.psp.kotlin.ApiService
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {
    @Mock
    private lateinit var apiService: ApiService
    //private lateinit var repositorio: AlumnoRepository

    /*@Before
    fun setUp() {
        repositorio = AlumnoRepository(apiService)
    }*/

    @Test
    fun `getAlumnos returns succes with list of alumnos`() =
        runTest {
            val mockAlumnos = listOf(
                Alumno(
                    1,
                    "Juan",
                    "1999-01-01",
                    Curso.DAM1,
                    "juanEmail@gmail.com",
                    listOf(Asignatura.PSP, Asignatura.SGE)
                ),
                Alumno(
                    2,
                    "Pedro",
                    "1998-01-01",
                    Curso.DAM2,
                    "pedroEmail@mail.com",
                    listOf(Asignatura.AAD, Asignatura.DDI)
                ),
                Alumno(3, "Ana", "1997-01-01", Curso.DAW1, "email3", listOf(Asignatura.PMDM)),
                Alumno(
                    4,
                    "Maria",
                    "1996-01-01",
                    Curso.DAW2,
                    "email4",
                    listOf(Asignatura.PSP, Asignatura.SGE, Asignatura.AAD)
                )
            )

            whenever(apiService.getAlumnos()).thenReturn(
                Response.success(mockAlumnos)
            )

            //val resultado = repositorio.getAlumnos()
            val resultado = apiService.getAlumnos().body()

            //Se compara
            //assertTrue(resultado.isSuccess)
            //assertEquals(mockAlumnos, resultado.getOrNull())
            assertTrue(resultado != null)
            assertEquals(mockAlumnos, resultado)
        }

    @Test
    fun `getAlumnos returns error`() =
        runTest {
            whenever(apiService.getAlumnos()).thenReturn(
                Response.error(404, "Not found".toResponseBody())
            )

            //val resultado = repositorio.getAlumnos()
            val resultado = apiService.getAlumnos()

            //Se compara
            //assertTrue(resultado.isFailure)
            assertTrue(resultado.errorBody() != null)
        }

    @Test
    fun `getAlumnosByCurso returns succes with list of alumnos`() =
        runTest {
            val mockAlumnos = listOf(
                Alumno(
                    1,
                    "Juan",
                    "1999-01-01",
                    Curso.DAM1,
                    "juanEmail@gmail.com",
                    listOf(Asignatura.PSP, Asignatura.SGE)
                ),
                Alumno(
                    2,
                    "Pedro",
                    "1998-01-01",
                    Curso.DAM2,
                    "pedroEmail@mail.com",
                    listOf(Asignatura.AAD, Asignatura.DDI)
                ),
                Alumno(3, "Ana", "1997-01-01", Curso.DAW1, "email3", listOf(Asignatura.PMDM)),
                Alumno(
                    4,
                    "Maria",
                    "1996-01-01",
                    Curso.DAW2,
                    "email4",
                    listOf(Asignatura.PSP, Asignatura.SGE, Asignatura.AAD)
                )
            )

            whenever(apiService.getAlumnosByCurso("DAM1")).thenReturn(
                Response.success(mockAlumnos.filter { it.curso == Curso.DAM1 })
            )

            val resultado = apiService.getAlumnosByCurso("DAM1").body()

            //Se compara
            assertTrue(resultado != null)
            assertEquals(mockAlumnos.filter { it.curso == Curso.DAM1 }, resultado)
        }

    @Test
    fun `getAlumnosByNombre returns succes with list of alumnos`() =
        runTest {
            val mockAlumnos = listOf(
                Alumno(
                    1,
                    "Juan",
                    "1999-01-01",
                    Curso.DAM1,
                    "juanEmail@gmail.com",
                    listOf(Asignatura.PSP, Asignatura.SGE)
                ),
                Alumno(
                    2,
                    "Pedro",
                    "1998-01-01",
                    Curso.DAM2,
                    "pedroEmail@mail.com",
                    listOf(Asignatura.AAD, Asignatura.DDI)
                ),
                Alumno(3, "Ana", "1997-01-01", Curso.DAW1, "email3", listOf(Asignatura.PMDM)),
                Alumno(
                    4,
                    "Maria",
                    "1996-01-01",
                    Curso.DAW2,
                    "email4",
                    listOf(Asignatura.PSP, Asignatura.SGE, Asignatura.AAD)
                )
            )

            whenever(apiService.getAlumnoByNombre("Juan")).thenReturn(
                Response.success(mockAlumnos.filter { it.nombre == "Juan" })
            )

            val resultado = apiService.getAlumnoByNombre("Juan").body()

            //Se compara
            assertTrue(resultado != null)
            assertEquals(mockAlumnos.filter { it.nombre == "Juan" }, resultado)

        }

    @Test
    fun `addAlumno returns succes with alumno added`() =
        runTest {
            val mockAlumnos = listOf(
                Alumno(
                    1,
                    "Juan",
                    "1999-01-01",
                    Curso.DAM1,
                    "juanEmail@gmail.com",
                    listOf(Asignatura.PSP, Asignatura.SGE)
                ),
                Alumno(
                    2,
                    "Pedro",
                    "1998-01-01",
                    Curso.DAM2,
                    "pedroEmail@mail.com",
                    listOf(Asignatura.AAD, Asignatura.DDI)
                ),
                Alumno(3, "Ana", "1997-01-01", Curso.DAW1, "email3", listOf(Asignatura.PMDM)),
                Alumno(
                    4,
                    "Maria",
                    "1996-01-01",
                    Curso.DAW2,
                    "email4",
                    listOf(Asignatura.PSP, Asignatura.SGE, Asignatura.AAD)
                )
            )

            whenever(apiService.addAlumno(mockAlumnos[0])).thenReturn(
                Response.success(mockAlumnos[0])
            )

            val resultado = apiService.addAlumno(mockAlumnos[0]).body()

            //Se compara
            assertTrue(resultado != null)
            assertEquals(mockAlumnos[0], resultado)

        }

    //TODO: Test deleteAlumno al no devolver nada no se como testearlo
}