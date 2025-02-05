package com.psp.retrofit

import com.psp.data.AlumnoDataRepository
import com.psp.data.AlumnoService
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
//import kotlinx.coroutines.test.runTest
import okhttp3.Response
import org.mockito.kotlin.any

@RunWith(MockitoJUnitRunner::class)
class AlumnoRepositoryTest {

    @Mock
    private lateinit var alumnoService: AlumnoService

    private lateinit var alumnoRepository: AlumnoDataRepository

    companion object {
        val alumnos = listOf(
            Alumno(1, "Roberto", "14/05/2004", Curso.DAM2, "roberto04@email.com", listOf(Asignatura.PSP, Asignatura.AAD)),
            Alumno(2, "Sofia", "04/09/2001", Curso.DAM1, "sofia01@email.com", listOf(Asignatura.EIE, Asignatura.PMDM, Asignatura.PSP)),
        )
        val alumnoUnico = alumnos[0]
    }

    @Before
    fun setUp() {
        alumnoRepository = AlumnoDataRepository(alumnoService)
    }

    /*
    @Test
    fun `getAlumnos should return expected list`() = runTest {
        whenever(alumnoService.getAlumnos()).thenReturn(alumnos)

        val result = alumnoRepository.getAlumnos()

        assertEquals(alumnos, result)
        verify(alumnoService).getAlumnos()
    }

    @Test
    fun `getAlumnoById should return correct student`() = runTest {
        whenever(alumnoService.getAlumnoId(1)).thenReturn(alumnoUnico)

        val result = alumnoRepository.getAlumnoById(1)

        assertEquals(alumnoUnico, result)
        verify(alumnoService).getAlumnoId(1)
    }

    @Test
    fun `getAlumnoByNombre should return correct student`() = runTest {
        whenever(alumnoService.getAlumnoNombre("Roberto")).thenReturn(alumnoUnico)

        val result = alumnoRepository.getAlumnoByNombre("Roberto")

        assertEquals(alumnoUnico, result)
        verify(alumnoService).getAlumnoNombre("Roberto")
    }

    @Test
    fun `getAlumnoByCurso should return students of course`() = runTest {
        whenever(alumnoService.getAlumnoCurso("DAM2")).thenReturn(alumnos)

        val result = alumnoRepository.getAlumnoByCurso("DAM2")

        assertEquals(alumnos, result)
        verify(alumnoService).getAlumnoCurso("DAM2")
    }

    @Test
    fun `addAlumno should add student successfully`() = runTest {
        whenever(alumnoService.addAlumno(alumnoUnico)).thenReturn(alumnoUnico)

        val result = alumnoRepository.addAlumno(alumnoUnico)

        assertEquals(alumnoUnico, result)
        verify(alumnoService).addAlumno(alumnoUnico)
    }

    @Test
    fun `deleteAlumno should remove student successfully`() = runTest {
        whenever(alumnoService.deleteAlumno(1)).thenReturn(true)

        val result = alumnoRepository.deleteAlumno(1)

        assertTrue(result)
        verify(alumnoService).deleteAlumno(1)
    }
*/
}
