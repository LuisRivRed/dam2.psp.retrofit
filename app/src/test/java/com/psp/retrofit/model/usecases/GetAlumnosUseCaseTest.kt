package com.psp.retrofit.model.usecases

import com.psp.model.Alumno
import com.psp.model.AlumnoRepository
import com.psp.model.Curso
import com.psp.model.usecases.GetAlumnosUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetAlumnosUseCaseTest {

    @Mock
    private lateinit var repository: AlumnoRepository
    private lateinit var useCase: GetAlumnosUseCase

    @Before
    fun setUp() {
        useCase = GetAlumnosUseCase(repository)
    }

    @Test
    fun `when invoked, it returns a list of alumnos`() = runTest {
        // Given
        val expectedAlumnos = Result.success(listOf(
            Alumno(1, "Pepe", "Pérez", Curso.DAM2, "educa@email", emptyList()))
        )
        whenever(repository.getAlumnos()).thenReturn(expectedAlumnos)

        // When
        val result = useCase.invoke()

        // Then
        assert(result == expectedAlumnos)
    }
}