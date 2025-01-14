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
}