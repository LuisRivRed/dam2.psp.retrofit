package com.psp.retrofit

import com.psp.data.AlumnoDataRepository
import com.psp.data.AlumnoApiService
import com.psp.domain.model.Alumno
import com.psp.domain.model.Asignatura
import com.psp.domain.model.Curso
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AlumnoApiServiceTest {

    @Mock
    private lateinit var alumnoApiService: AlumnoApiService
    private lateinit var repository: AlumnoDataRepository


    @Before
    fun setup() {
        repository = AlumnoDataRepository(alumnoApiService)
    }

    // Lo que esperamos que devuelva:
    private val mockAlumnos = Response.success(
        listOf(
            Alumno(
                1, "Pedro", "05/06/2000",
                Curso.DAM1, "pedro@educa.jcyl.es",
                listOf(Asignatura.AAD, Asignatura.PMDM, Asignatura.EIE)
            ),
            Alumno(
                2, "Pepe", "12/02/2001", Curso.DAM2,
                "pepe@educa.jcyl.es", listOf(Asignatura.AAD, Asignatura.PSP)
            ),
            Alumno(
                3, "Alicia",
                "05/06/2000", Curso.DAW1,
                "alicia@educa.jcyl.es", listOf(Asignatura.DDI, Asignatura.AAD, Asignatura.PSP)
            )
        )
    )

    private val mockAlumno = Response.success(
        Alumno(
            1, "Pedro", "05/06/2000",
            Curso.DAM1, "pedro@educa.jcyl.es",
            listOf(Asignatura.AAD, Asignatura.PMDM, Asignatura.EIE)
        )
    )

    @Test
    fun getAlumnosTest() {
        runTest {
            whenever(alumnoApiService.getAlumnos()).thenReturn(
                mockAlumnos
            )

            val response = repository.getAlumnos()

            //se compara:
            assertTrue(response.isSuccessful)
            assertEquals(mockAlumnos, response)
        }
    }

    @Test
    fun getAlumnoByNombre() {
        val name = "Pedro"
        runTest {
            whenever(alumnoApiService.getAlumnoNombre(name)).thenReturn(mockAlumno)

            val response = repository.getAlumnoByNombre(name)

            // se compara:
            assertTrue(response.isSuccessful)
            assertEquals(mockAlumno, response)
        }
    }

    @Test
    fun getAlumnosByCurso() {
        val curso = "DAM1"
        runTest {
            whenever(alumnoApiService.getAlumnoCurso(curso)).thenReturn(mockAlumnos)

            val response = repository.getAlumnoByCurso(curso)

            //se compara:
            assertTrue(response.isSuccessful)
            assertEquals(mockAlumnos, response)
        }
    }

    @Test
    fun addAlumno() {
        val newAlumno =
            Alumno(
                id = 6,
                nombre = "Marcos",
                fechaNacimiento = "14/06/2003",
                curso = Curso.DAW2,
                email = "marcos12@gmail.com",
                asignaturas = listOf(Asignatura.PSP, Asignatura.EIE, Asignatura.PMDM)
            )

        runTest {
            whenever(alumnoApiService.addAlumno(newAlumno)).thenReturn(mockAlumno)

            val response = repository.addAlumno(newAlumno)

            // se compara:
            assertTrue(response.isSuccessful)
            assertEquals(mockAlumno, response)
        }
    }

    @Test
    fun deleteAlumno() {
        val idAlumno = 1
        runTest {
            whenever(alumnoApiService.deleteAlumno(idAlumno)).thenReturn(Response.success(Unit))
            val response = repository.deleteAlumno(idAlumno)


            //se compara:
            assertTrue(response.isSuccessful)
            assertEquals(200,response.code())
        }
    }


}