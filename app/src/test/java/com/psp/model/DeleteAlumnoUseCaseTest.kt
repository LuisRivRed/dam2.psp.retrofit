package com.psp.model

import com.psp.data.AlumnoDataRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class DeleteAlumnoUseCaseTest {
    @RelaxedMockK
    private lateinit var repositoryMock: AlumnoDataRepository

    lateinit var deleteAlumnoUseCase: DeleteAlumnoUseCase

    @Before
    fun before() {
        MockKAnnotations.init(this)
        deleteAlumnoUseCase = DeleteAlumnoUseCase(repositoryMock)
    }

    @Test
    fun llamaAlRepositorioParaEliminarUnAlumnoSinProblemas() = runBlocking {
        //Given
        //When
        val resultado = runCatching {deleteAlumnoUseCase.invoke(1)}
        //Them
        assert(resultado.isSuccess)
        coVerify (exactly = 1){ repositoryMock.deleteAlumno(any()) }
    }

    @Test
    fun llamaAlRepositorioParaEliminarUnAlumnoYLaApiLanzaUnaExepcion() = runBlocking {
        //Given
        coEvery { repositoryMock.deleteAlumno(any()) } throws Exception("Algo fallo al eliminar el alumno")
        //When
        val resultado = runCatching {deleteAlumnoUseCase.invoke(4)}
        //Them
        assert(resultado.isFailure)
        assert("Algo fallo al eliminar el alumno" == resultado.exceptionOrNull()?.message)
        coVerify (exactly = 1){ repositoryMock.deleteAlumno(any()) }
    }
}