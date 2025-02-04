package com.psp.data

import com.psp.model.Asignatura
import com.psp.model.Curso
import junit.framework.TestCase.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class AlumnoServiceTest {

    @Mock
    private lateinit var alumnoService: AlumnoService

    private val token = "token"
    private val mockAlumno = AlumnoApiModel(
        "1", "Iker", "05/06/2001", Curso.DAM1, "iker@gmail.com",
        listOf(Asignatura.PSP, Asignatura.DDI, Asignatura.PMDM)
    )

    private val mockListAlumnos = listOf(
        mockAlumno,
        AlumnoApiModel("2", "Carlos", "15/03/2000", Curso.DAM2, "carlos123@gmail.com", listOf(Asignatura.AAD, Asignatura.EIE, Asignatura.PSP)),
        AlumnoApiModel("3", "Lucía", "22/09/1999", Curso.DAW1, "lucia99@gmail.com", listOf(Asignatura.AAD, Asignatura.PMDM, Asignatura.PSP)),
        AlumnoApiModel("4", "Marco", "18/11/2002", Curso.DAW2, "marco88@gmail.com", listOf(Asignatura.PSP, Asignatura.PMDM, Asignatura.DDI))
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getListaDeAlumnos() = runTest {
        whenever(alumnoService.getAlumnos(token)).thenReturn(Response.success(mockListAlumnos))
        val result = alumnoService.getAlumnos(token)
        assertTrue(result.isSuccessful)
        assertEquals(mockListAlumnos, result.body())
    }

    @Test
    fun getAlumnosRespuestaNula() = runTest {
        whenever(alumnoService.getAlumnos(token)).thenReturn(Response.success(null))
        val result = alumnoService.getAlumnos(token)
        assertTrue(result.isSuccessful)
        assertNull(result.body())
    }

    @Test
    fun getAlumnoPorCurso() = runTest {
        val curso = Curso.DAM1
        whenever(alumnoService.getAlumno(curso.toString())).thenReturn(Response.success(listOf(mockAlumno)))
        val result = alumnoService.getAlumno(curso.toString())
        assertTrue(result.isSuccessful)
        assertEquals(listOf(mockAlumno), result.body())
    }

    @Test
    fun getAlumnoPorCursoConRespuestaNula() = runTest {
        val curso = Curso.DAM1
        whenever(alumnoService.getAlumno(curso.toString())).thenReturn(Response.success(null))
        val result = alumnoService.getAlumno(curso.toString())
        assertTrue(result.isSuccessful)
        assertNull(result.body())
    }

    @Test
    fun getAlumnoPorNombre() = runTest {
        val nombre = "Iker"
        whenever(alumnoService.getAlumnoPorNombre(nombre)).thenReturn(Response.success(listOf(mockAlumno)))
        val result = alumnoService.getAlumnoPorNombre(nombre)
        assertTrue(result.isSuccessful)
        assertEquals(listOf(mockAlumno), result.body())
    }

    @Test
    fun getAlumnoPorNombreConRespuestaNula() = runTest {
        val nombre = "Iker"
        whenever(alumnoService.getAlumnoPorNombre(nombre)).thenReturn(Response.success(null))
        val result = alumnoService.getAlumnoPorNombre(nombre)
        assertTrue(result.isSuccessful)
        assertNull(result.body())
    }

    @Test
    fun agregarAlumno() = runTest {
        whenever(alumnoService.addAlumno(mockAlumno)).thenReturn(Response.success(mockAlumno))
        val result = alumnoService.addAlumno(mockAlumno)
        assertTrue(result.isSuccessful)
        assertEquals(mockAlumno, result.body())
    }

    @Test
    fun eliminarAlumno() = runTest {
        val id = "1"
        whenever(alumnoService.deleteAlumno(id)).thenReturn(Response.success(Unit))
        val result = alumnoService.deleteAlumno(id)
        assertTrue(result.isSuccessful)
        assertEquals(Unit, result.body())
    }

}