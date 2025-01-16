package com.psp.model

import com.psp.data.AlumnoDataRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetAlumnoByNombreUseCaseTest{
    @RelaxedMockK
    private lateinit var repositoryMock: AlumnoDataRepository

    lateinit var getAlumnoByNombreUseCase: GetAlumnoByNombreUseCase
    @Before
    fun before(){
        MockKAnnotations.init(this)
        getAlumnoByNombreUseCase=GetAlumnoByNombreUseCase(repositoryMock)
    }

    @Test
    fun cuandoBuscoUnAlumnoQueExistePorSuNombreMeLoDevuelve()= runBlocking {
        //Given
        var alumnoExample= Alumno(
            5,
            "Ian",
            "2004/05/10",
            Curso.DAM2, "ian@example.com",
            listOf(Asignatura.PSP, Asignatura.SGE, Asignatura.AAD))
        coEvery {  repositoryMock.getAlumnoByName(any())} returns alumnoExample
        //When
        val result = getAlumnoByNombreUseCase.invoke("Ian")
        //Them
        coVerify (exactly = 1){ repositoryMock.getAlumnoByName(any()) }
        assert(result == alumnoExample)
    }
    @Test
    fun cuandoBuscoUnAlumnoQueNoExisteMeDevuelveNull()= runBlocking {
        //Given
        coEvery {  repositoryMock.getAlumnoByName(any())} returns null
        //When
        val result = getAlumnoByNombreUseCase.invoke("Ian")
        //Them
        coVerify (exactly = 1){ repositoryMock.getAlumnoByName(any()) }
        assert(result == null)
    }
}