package com.psp.retrofit.model.usecases

import com.psp.model.Alumno
import com.psp.model.AlumnoRepository
import com.psp.model.Curso
import com.psp.model.usecases.DeleteAlumnoByIdUseCase
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class DeleteAlumnoByIdUseCaseTest {

    @Mock
    private lateinit var repository: AlumnoRepository
    private lateinit var deleteAlumnoByIdUseCase: DeleteAlumnoByIdUseCase


    @Before
    fun setUp() {
        deleteAlumnoByIdUseCase = DeleteAlumnoByIdUseCase(repository)
    }

    @Test
    fun `when invoked, the alumno with the id is deleted`() = runTest {
        // Given
        val mockAlumnos = mutableListOf(
            Alumno(1, "Pepe", "Pérez", Curso.DAM1, "educa@email", emptyList()),
            Alumno(2, "Juan", "Gómez", Curso.DAM2, "educa@email", emptyList())
        )
        val id = 1
        whenever(repository.deleteAlumnoById(id)).then {
            mockAlumnos.removeIf { it.id == id }
        }
        // When
        deleteAlumnoByIdUseCase.invoke(id)

        // Then
        verify(repository, times(1)).deleteAlumnoById(id)
        assert(mockAlumnos.size == 1)
        assertNull(mockAlumnos.find { it.id == id })
    }

    @Test
    fun `when invoked and id doesn't match any element, it do`() = runTest {
        // Given
        val mockAlumnos = mutableListOf(
            Alumno(1, "Pepe", "Pérez", Curso.DAM1, "educa@email", emptyList()),
            Alumno(2, "Juan", "Gómez", Curso.DAM2, "educa@email", emptyList())
        )
        val id = 0
        whenever(repository.deleteAlumnoById(id)).then {
            mockAlumnos.removeIf { it.id == id }
        }
        // When
        deleteAlumnoByIdUseCase.invoke(id)

        // Then
        verify(repository, times(1)).deleteAlumnoById(id)
        assert(mockAlumnos.size == 2)
    }
}