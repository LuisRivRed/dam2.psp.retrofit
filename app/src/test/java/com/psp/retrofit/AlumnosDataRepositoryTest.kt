package com.psp.retrofit

import com.psp.data.AlumnoDataRepository
import com.psp.data.ApiService
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AlumnosDataRepositoryTest {

    // Crear un mock de ApiService
    @Mock
    private lateinit var apiService: ApiService
    private lateinit var alumnoDataRepository: AlumnoDataRepository

    // Crear una instancia de AlumnoDataRepository
    @Before
    fun setUp() {
        alumnoDataRepository = AlumnoDataRepository(apiService)
    }

    // Prueba para el método getAlumnos de AlumnoDataRepository
    @Test
    fun `getStudents returns list of students`() = runTest {
        val mockAlumnos = listOf(
            Alumno(
                1,
                "Pedro",
                "05/06/2000",
                Curso.DAM1,
                "pedro@educa.jcyl.es",
                listOf(Asignatura.PSP, Asignatura.PMDM, Asignatura.EIE)
            ),
            Alumno(
                2,
                "Pepe",
                "12/02/2001",
                Curso.DAM2,
                "pepe@educa.jcyl.es",
                listOf(Asignatura.AAD, Asignatura.PSP)
            ),
            Alumno(
                3,
                "Juan",
                "15/09/2002",
                Curso.DAW1,
                "juan@educa.jcuyl.es",
                listOf(Asignatura.PSP)
            )
        )
        // Configurar el mock para que devuelva la lista de alumnos,
        // de por si el mock tiene los métodos pero no hacen nada
        whenever(apiService.getAlumnos()).thenReturn(Response.success(mockAlumnos))

        // Llamada al repository
        val response = alumnoDataRepository.getAlumnos()

        //Verifica las respuestas y compara
        assertEquals(200, response.code())
        assertEquals(mockAlumnos, response.body())
    }

    @Test
    fun `test getAlumnoById should return alumno with given id`() = runTest {
        val mockAlumnos = listOf(
            Alumno(
                1,
                "Pedro",
                "05/06/2000",
                Curso.DAM1,
                "pedro@educa.jcyl.es",
                listOf(Asignatura.PSP, Asignatura.PMDM, Asignatura.EIE)
            )
        )
        whenever(apiService.getAlumnoById(id = 1)).thenReturn(Response.success(mockAlumnos[0]))

        val response = alumnoDataRepository.getAlumnoById(id = 1)
        assertEquals(200, response.code())
        assertEquals(mockAlumnos[0], response.body())
    }

    @Test
    fun `getAlumnoByNombre should return a list of alumnos with given curso`() = runTest {
        val mockAlumnos = listOf(
            Alumno(
                2,
                "Pepe",
                "12/02/2001",
                Curso.DAM2,
                "pepe@educa.jcyl.es",
                listOf(Asignatura.AAD, Asignatura.PSP)
            )
        )
        whenever(apiService.getAlumnosByCurso(curso = Curso.DAM2)).thenReturn(
            Response.success(
                mockAlumnos
            )
        )

        val response = alumnoDataRepository.getAlumnobyCurso(curso = Curso.DAM2)
        assertEquals(200, response.code())
        assertEquals(mockAlumnos, response.body())

    }

    @Test

    fun `getAlumnoByCurso should return a list of alumnos with given curso`() = runTest {
        val mockAlumnos = listOf(
            Alumno(
                1,
                "Pedro",
                "05/06/2000",
                Curso.DAM1,
                "pedro@educa.jcyl.es",
                listOf(Asignatura.PSP, Asignatura.PMDM, Asignatura.EIE)
            ),
            Alumno(
                2,
                "Pepe",
                "12/02/2001",
                Curso.DAM2,
                "pepe@educa.jcyl.es",
                listOf(Asignatura.AAD, Asignatura.PSP)
            ),
            Alumno(
                3,
                "Juan",
                "15/09/2002",
                Curso.DAW1,
                "juan@educa.jcuyl.es",
                listOf(Asignatura.PSP)
            )
        )
        whenever(apiService.getAlumnosByCurso(curso = Curso.DAM2)).thenReturn(
            Response.success(
                mockAlumnos
            )
        )

        val response = alumnoDataRepository.getAlumnobyCurso(curso = Curso.DAM2)
        assertEquals(200, response.code())
        assertEquals(mockAlumnos, response.body())

    }

    @Test
    fun `deleteAlumno should return true if alumno is deleted successfully`() = runTest {
        val mockAlumnos = listOf(
            Alumno(
                1,
                "Pedro",
                "05/06/2000",
                Curso.DAM1,
                "pedro@educa.jcyl.es",
                listOf(Asignatura.PSP, Asignatura.PMDM, Asignatura.EIE)
            ),
            Alumno(
                2,
                "Pepe",
                "12/02/2001",
                Curso.DAM2,
                "pepe@educa.jcyl.es",
                listOf(Asignatura.AAD, Asignatura.PSP)
            )
        )
        whenever(apiService.deleteAlumno(id = 1)).thenReturn(Response.success(true))

        val response = alumnoDataRepository.deleteAlumno(id = 1)
        assertEquals(200, response.code())
        assertEquals(true, response.body())
    }

    @Test
    fun `addAlumno should return the added alumno`() = runTest {
        val newAlumno = Alumno(
            4,
            "Mario",
            "13/01/2008",
            Curso.DAM1,
            "mario@educa.jcyl.es",
            listOf(Asignatura.AAD, Asignatura.PSP)
        )

        whenever(apiService.addAlumno(alumno = newAlumno)).thenReturn(Response.success(newAlumno))

        val response = alumnoDataRepository.addAlumno(alumno = newAlumno)
        assertEquals(200, response.code())
        verify(apiService).addAlumno(alumno = newAlumno)

    }
}