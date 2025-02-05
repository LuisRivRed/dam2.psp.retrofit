package com.psp.retrofit.model.usecases

import com.psp.model.Alumno
import com.psp.model.AlumnoRepository
import com.psp.model.Curso
import com.psp.model.usecases.GetAlumnoByNameUseCase
import junit.framework.TestCase.assertEquals
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
    fun `should return alumno when API call is successful`() = runTest {
        val alumno = Result.success(Alumno (
            id = 1,
            nombre = "Pepe",
            curso = Curso.DAM2,
            fechaNacimiento = "1990-01-01",
            email = "pepe@email.com",
            asignaturas = emptyList(),
        ))
        whenever(repository.getAlumnosByName("Pepe")).thenReturn(alumno)

        //When
        val result = useCase.invoke("Pepe")

        // Then
        assert(result.isSuccess)
        assertEquals(result, alumno)
    }
}