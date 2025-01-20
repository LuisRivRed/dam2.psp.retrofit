package com.psp.retrofit.model.usecases

import com.psp.model.Alumno
import com.psp.model.AlumnoRepository
import com.psp.model.Curso
import com.psp.model.usecases.SaveAlumnoUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class SaveAlumnoUseCaseTest {

    @Mock
    private lateinit var repository: AlumnoRepository
    private lateinit var useCase: SaveAlumnoUseCase

    @Before
    fun setUp() {
        useCase = SaveAlumnoUseCase(repository)
    }
    @Test
    fun `when invoked, the alumno is saved`() = runTest {
        // Given
        val alumno = Alumno(1, "Pepe", "Pérez", Curso.DAM1, "educa@email", emptyList())
        // When
        useCase.invoke(alumno)
        // Then
        verify(repository, times(1)).addAlumno(alumno)
    }
}