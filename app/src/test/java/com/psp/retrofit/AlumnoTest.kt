package com.psp.retrofit

import com.psp.data.AlumnoApiService
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AlumnoTest {

    @Mock
    private lateinit var alumnoApiService: AlumnoApiService

    /*@Before
    fun setUp() {
        repositorio = AlumnoRepository(apiService)
    }*/

    @Test
    fun `test getAlumnos`() = runBlocking {
        val mockAlumnos = listOf(
            Alumno(1, "Carlos", "2001-05-05", Curso.DAW1, "carlos@example.com", listOf(Asignatura.DDI, Asignatura.EIE)),
            Alumno(2, "Lucía", "2000-06-06", Curso.DAM1, "lucia@example.com", listOf(Asignatura.AAD, Asignatura.PMDM)),
            Alumno(3, "Mateo", "1998-07-07", Curso.DAW2, "mateo@example.com", listOf(Asignatura.PSP)),
            Alumno(4, "Sofía", "1999-08-08", Curso.DAM2, "sofia@example.com", listOf(Asignatura.EIE, Asignatura.AAD))
        )

        whenever(alumnoApiService.getAlumnos()).thenReturn(
            Response.success(mockAlumnos)
        )
        val resultado = alumnoApiService.getAlumnos().body()
        assertTrue(resultado != null)
        assertEquals(mockAlumnos, resultado)
    }

    @Test
    fun `test getAlumnosByCurso`() = runBlocking {
        val mockAlumnos = listOf(
            Alumno(
                1,
                "Carlos",
                "2001-03-03",
                Curso.DAW1,
                "carlos@example.com",
                listOf(Asignatura.DDI)
            )
        )
        whenever(alumnoApiService.getAlumnosByCurso("DAM1")).thenReturn(
            Response.success(mockAlumnos.filter { it.curso == Curso.DAM1 })
        )
        val resultado = alumnoApiService.getAlumnosByCurso("DAM1").body()
        //Se compara
        assertTrue(resultado != null)
        assertEquals(mockAlumnos.filter { it.curso == Curso.DAM1 }, resultado)
    }

    @Test
    fun `test getAlumnoByNombre`() = runBlocking {
        val mockAlumnos = listOf(
            Alumno(1, "Juan", "2000-01-01", Curso.DAM1, "juan@example.com", listOf(Asignatura.AAD))
        )
        whenever(alumnoApiService.getAlumnoByNombre("Juan")).thenReturn(
            Response.success(mockAlumnos.filter { it.nombre == "Juan" })
        )
        val resultado = alumnoApiService.getAlumnoByNombre("Juan").body()
        assertTrue(resultado != null)
        assertEquals(mockAlumnos.filter { it.nombre == "Juan" }, resultado)
    }

    @Test
    fun `test addAlumno`() = runBlocking {
        val mockAlumnos = listOf(
            Alumno(1, "Carlos", "2001-05-05", Curso.DAW1, "carlos@example.com", listOf(Asignatura.DDI, Asignatura.EIE)),
            Alumno(2, "Lucía", "2000-06-06", Curso.DAM1, "lucia@example.com", listOf(Asignatura.AAD, Asignatura.PMDM)),
            Alumno(3, "Mateo", "1998-07-07", Curso.DAW2, "mateo@example.com", listOf(Asignatura.PSP)),
            Alumno(4, "Sofía", "1999-08-08", Curso.DAM2, "sofia@example.com", listOf(Asignatura.EIE, Asignatura.AAD))
        )
        whenever(alumnoApiService.addAlumno(mockAlumnos[0])).thenReturn(
            Response.success(mockAlumnos[0])
        )
        val resultado = alumnoApiService.addAlumno(mockAlumnos[0]).body()
        assertTrue(resultado != null)
        assertEquals(mockAlumnos[0], resultado)
    }

    @Test
    fun `test deleteAlumno`() = runBlocking {
        whenever(alumnoApiService.deleteAlumno(idAlumno = 1))
            .thenReturn(Response.success(null))

        val response = alumnoApiService.deleteAlumno(idAlumno = 1)

        assertTrue(response.isSuccessful)
        assertEquals(200, response.code())

        verify(alumnoApiService, times(1)).deleteAlumno(idAlumno = 1)
    }
}
