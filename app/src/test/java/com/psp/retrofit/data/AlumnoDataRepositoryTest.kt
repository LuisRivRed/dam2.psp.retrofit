package com.psp.retrofit.data


import com.psp.data.AlumnoDataRepository
import com.psp.data.remote.api.AlumnoApiDataSource
import com.psp.model.Alumno
import com.psp.model.Curso
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import kotlin.Result

@RunWith(MockitoJUnitRunner::class)
class AlumnoDataRepositoryTest {

    @Mock
    private lateinit var remoteDataSource: AlumnoApiDataSource
    private lateinit var dataRepository: AlumnoDataRepository

    @Before
    fun setUp() {
        dataRepository = AlumnoDataRepository(remoteDataSource)
    }

    @Test
    fun `getAlumnos returns list of alumnos`() = runTest {
        // Given
        val expectedAlumnos = Result.success(listOf(
            Alumno(1, "Pepe", "Pérez", Curso.DAM1, "educa@email", emptyList()))
        )
        whenever(remoteDataSource.getAlumnos()).thenReturn(expectedAlumnos)

        // When
        val result = dataRepository.getAlumnos()

        // Then
        assertEquals(expectedAlumnos, result)
    }

    @Test
    fun `getAlumnosByCurso returns list of alumnos for given curso`() = runTest {
        // Given
        val curso = Curso.DAM1
        val expectedAlumnos = Result.success(listOf(
            Alumno(1, "Pepe", "Pérez", Curso.DAM1, "educa@email", emptyList()))
        )
        whenever(remoteDataSource.getAlumnosByCurso(curso)).thenReturn(expectedAlumnos)

        // When
        val result = dataRepository.getAlumnosByCurso(curso)

        // Then
        assertEquals(expectedAlumnos, result)
    }

    @Test
    fun `getAlumnosByName returns list of alumnos for given name`() = runTest {
        // Given
        val name = "Pepe"
        val expectedAlumno = Result.success(Alumno(1, "Pepe", "Pérez", Curso.DAM1, "educa@email", emptyList()))
        whenever(remoteDataSource.getAlumnosByName(name)).thenReturn(expectedAlumno)

        // When
        val result = dataRepository.getAlumnosByName(name)

        // Then
        assertEquals(expectedAlumno, result)
    }

    @Test
    fun `addAlumno adds an alumno`() = runTest {
        // Given
        val alumno = Alumno(1, "Pepe", "Pérez", Curso.DAM1, "educa@email", emptyList())
        whenever(remoteDataSource.addAlumno(alumno)).thenReturn((Result.success(Unit)))

        // When
        val result = dataRepository.addAlumno(alumno)

        // Then
        verify(remoteDataSource, times(1)).addAlumno(alumno)
    }

    @Test
    fun `deleteAlumnoById deletes an alumno by id`() = runTest {
        // Given
        val id = 1
        whenever(remoteDataSource.deleteAlumnoById(id)).thenReturn(Result.success(Unit))

        // When
        val result = dataRepository.deleteAlumnoById(id)

        // Then
        verify(remoteDataSource, times(1)).deleteAlumnoById(id)
    }
}