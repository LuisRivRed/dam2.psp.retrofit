package com.psp.data

import com.psp.model.Asignatura
import com.psp.model.Curso
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

class AlumnoServiceTest {
    @Mock
    private lateinit var alumnoService: AlumnoService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test getAlumnos`() = runTest {
        val mockAlumno = listOf(
            AlumnoApiModel(
                "1",
                "Alejandro",
                "05/06/2001",
                Curso.DAM1,
                "alejandro@gmail.com",
                listOf(Asignatura.PSP, Asignatura.DDI, Asignatura.PMDM)
            )
        )
        val response = Response.success(mockAlumno)
        `when`(alumnoService.getAlumnos()).thenReturn(response)
        val result = alumnoService.getAlumnos()
        assertEquals(response, result)
    }

    @Test
    fun `test getAlumnoByCurso`() = runTest {
        val curso = Curso.DAM1
        val mockAlumno = listOf(
            AlumnoApiModel(
                "1",
                "Alejandro",
                "05/06/2001",
                curso,
                "alejandro@gmail.com",
                listOf(
                    Asignatura.PSP,
                    Asignatura.DDI,
                    Asignatura.PMDM
                )
            )
        )
        val response = Response.success(mockAlumno)
        `when`(alumnoService.getAlumno(curso.toString())).thenReturn(response)


        val result = alumnoService.getAlumno(curso.toString())
        assertEquals(response, result)
    }
}