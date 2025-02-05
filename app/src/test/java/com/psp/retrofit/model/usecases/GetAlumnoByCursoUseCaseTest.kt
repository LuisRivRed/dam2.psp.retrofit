package com.psp.retrofit.model.usecases

import com.psp.model.Alumno
import com.psp.model.AlumnoRepository
import com.psp.model.Curso
import com.psp.model.usecases.GetAlumnosByCursoUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetAlumnoByCursoUseCaseTest {


    @Mock
    private lateinit var repository: AlumnoRepository
    private lateinit var getAlumnosByCursoUseCase: GetAlumnosByCursoUseCase

    @Before
    fun setUp() {
        getAlumnosByCursoUseCase = GetAlumnosByCursoUseCase(repository)
    }

    @Test
    fun `when invoked, the alumnos with the curso are returned`() = runTest {
        // Given
        val expectedAlumnos = Result.success(listOf(
            Alumno(1, "Pepe", "Pérez", Curso.DAM2, "educa@email", emptyList()))
        )
        whenever(repository.getAlumnosByCurso(Curso.DAM2)).thenReturn(expectedAlumnos)
        // When
        val result = getAlumnosByCursoUseCase.invoke(Curso.DAM2)
        // Then
        assert(result == expectedAlumnos)
    }

    @Test
    fun `when invoked and curso doesn't match any element, it returns an empty list`() = runTest {
        // Given
        val expectedAlumnos = Result.success(emptyList<Alumno>())
        whenever(repository.getAlumnosByCurso(Curso.DAM2)).thenReturn(expectedAlumnos)
        // When
        val result = getAlumnosByCursoUseCase.invoke(Curso.DAM2)
        // Then
        assert(result == expectedAlumnos)
    }
}