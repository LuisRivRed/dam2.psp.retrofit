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
import org.mockito.kotlin.mock
import retrofit2.Response

class AlumnoServiceTest {
    @Mock
    private lateinit var alumnoService: AlumnoService
    private var mockAlumno: AlumnoApiModel = AlumnoApiModel(
        "1",
        "Alejandro",
        "05/06/2001",
        Curso.DAM1,
        "alejandro@gmail.com",
        listOf(
            Asignatura.PSP,
            Asignatura.DDI,
            Asignatura.PMDM
        )
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test getAlumnos`() = runTest {
        val mockAlumno = listOf(mockAlumno)

        val response = Response.success(mockAlumno)
        `when`(alumnoService.getAlumnos()).thenReturn(response)
        val result = alumnoService.getAlumnos()
        assertEquals(response, result)
    }

    @Test
    fun `test getAlumnoByCurso`() = runTest {
        val curso = Curso.DAM1
        val mockAlumno = listOf(mockAlumno)
        val response = Response.success(mockAlumno)
        `when`(alumnoService.getAlumno(curso.toString())).thenReturn(response)


        val result = alumnoService.getAlumno(curso.toString())
        assertEquals(response, result)
    }

    @Test
    fun `test getAlumnoByNombre`() = runTest {
        val nombre = "Alejandro"
        val mockAlumno = listOf(mockAlumno)

        val response = Response.success(mockAlumno)
        `when`(alumnoService.getAlumnoPorNombre(nombre)).thenReturn(response)

        val result = alumnoService.getAlumnoPorNombre(nombre)
        assertEquals(response, result)
    }

    @Test
    fun `test addAlumno`() = runTest {
        val alumno = mockAlumno

        val response = Response.success(alumno)
        `when`(alumnoService.addAlumno(alumno)).thenReturn(response)

        val result = alumnoService.addAlumno(alumno)
        assertEquals(response, result)
    }

    @Test
    fun `test deleteAlumno`() = runTest {
        val id = "1"

        val response = Response.success(Unit)
        `when`(alumnoService.deleteAlumno(id)).thenReturn(response)

        val result = alumnoService.deleteAlumno(id)
        assertEquals(response, result)
    }
}