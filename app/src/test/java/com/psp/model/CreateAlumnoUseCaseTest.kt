package com.psp.model

import com.psp.data.AlumnoDataRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class CreateAlumnoUseCaseTest {
    @RelaxedMockK
    private lateinit var repositoryMock: AlumnoDataRepository

    lateinit var createAlumnoUseCase: CreateAlumnoUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        createAlumnoUseCase = CreateAlumnoUseCase(repositoryMock)
    }

    @Test
    fun deberiaLlamarAlRepositorioParaCrearAlumno() = runBlocking {
        //Given
        val alumnoDePrueba = Alumno(
            id = 1,
            nombre = "Juan Pérez",
            fechaNacimiento = "2005-03-15",
            curso = Curso.DAM1,
            email = "juan.perez@example.com",
            asignaturas = listOf(Asignatura.PSP)
        )
        //When
        val resultado = runCatching { createAlumnoUseCase.invoke(alumnoDePrueba)}
        //Them
        assert(resultado.isSuccess)
        coVerify(exactly = 1) { repositoryMock.saveAlumno(any()) }
    }

    @Test
    fun deberiaLanzarExcepcionCuandoElClienteDeRetrofitFalla() = runBlocking {
        //Given
        coEvery { repositoryMock.saveAlumno(any()) } throws Exception("Error al guardar el alumno")
        val alumnoDePrueba = Alumno(
            id = 1,
            nombre = "Juan Pérez",
            fechaNacimiento = "2005-03-15",
            curso = Curso.DAM1,
            email = "juan.perez@example.com",
            asignaturas = listOf(Asignatura.PSP)
        )
        //When
        val resultado = runCatching { createAlumnoUseCase.invoke(alumnoDePrueba) }
        //Them
        assert(resultado.isFailure)
        assert("Error al guardar el alumno" == resultado.exceptionOrNull()?.message)
        coVerify(exactly = 1) { repositoryMock.saveAlumno(any()) }
    }

}
