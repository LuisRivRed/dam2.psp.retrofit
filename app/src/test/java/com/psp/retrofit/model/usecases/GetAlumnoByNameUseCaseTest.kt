package com.psp.retrofit.model.usecases

import com.psp.model.Alumno
import com.psp.model.AlumnoRepository
import com.psp.model.Curso
import com.psp.model.usecases.GetAlumnoByNameUseCase
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetAlumnoByNameUseCaseTest {

    @Mock
    private lateinit var repository: AlumnoRepository
    private lateinit var useCase: GetAlumnoByNameUseCase


    @Before
    fun setUp() {
        useCase = GetAlumnoByNameUseCase(repository)
    }

    @Test
    fun `when invoked, the alumno with the name are returned`() = runTest {
        // Given
        val expectedAlumnos = listOf(
            Alumno(1, "Pepe", "Pérez", Curso.DAM2, "educa@email", emptyList()),
            Alumno(2, "Juan", "Gómez", Curso.DAM2, "educa@email", emptyList())
        )
        whenever(repository.getAlumnosByName("Pepe")).thenReturn(
            expectedAlumnos.find { it.nombre == "Pepe" }
        )
        // When
        val result = useCase.invoke("Pepe")
        // Then
        assert(result == expectedAlumnos[0])
    }

    @Test
    fun `when invoked and name doesn't match any element, it returns an empty list`() = runTest {
        // Given
        val expectedAlumnos = listOf(
            Alumno(1, "Pepe", "Pérez", Curso.DAM2, "educa@email", emptyList()),
            Alumno(2, "Juan", "Gómez", Curso.DAM2, "educa@email", emptyList())
        )
        whenever(repository.getAlumnosByName("María")).thenReturn(
            expectedAlumnos.find
            { it.nombre == "María" }
        )
        // When
        val result = useCase.invoke("María")
        // Then
        assertNull(result)
    }
}