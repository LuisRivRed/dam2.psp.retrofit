package com.psp.model

import com.psp.data.AlumnoDataRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetAlumnosByCursoUseCaseTest{
    @RelaxedMockK
    private lateinit var repositoryMockk: AlumnoDataRepository

    lateinit var getAlumnosByCursoUseCase: GetAlumnosByCursoUseCase

    @Before
    fun before(){
        MockKAnnotations.init(this)
        getAlumnosByCursoUseCase = GetAlumnosByCursoUseCase(repositoryMockk)
    }

    @Test
    fun sePidenLosAlumnosPorSuCursoYDevuelveDatos()= runBlocking{
        //Given
        val mockList = listOf(
            Alumno(
                id = 1,
                nombre = "Yisela",
                fechaNacimiento = "2003-06-19",
                curso = Curso.DAW2,
                email = "yisela03@example.com",
                asignaturas = listOf(Asignatura.PSP, Asignatura.AAD, Asignatura.PMDM)
            ),
            Alumno(
                id = 2,
                nombre = "Maribel",
                fechaNacimiento = "2005-01-02",
                curso = Curso.DAW2,
                email = "maribel05@example.com",
                asignaturas = listOf(Asignatura.DDI, Asignatura.SGE, Asignatura.EIE)
            )
        )
         coEvery { repositoryMockk.getAlumnoByCurso(any()) } returns mockList
        //When
            val result = getAlumnosByCursoUseCase.invoke("DAW2")
        //Then
        coVerify (exactly = 1){ repositoryMockk.getAlumnoByCurso(any()) }
        assert(result == mockList)
    }
    @Test
    fun sePidenLosAlumnosPorSuCursoYDevuelveUnaListaVacia()= runBlocking{
        //Given
            coEvery { repositoryMockk.getAlumnoByCurso(any()) } returns emptyList()
        //When
        val result = getAlumnosByCursoUseCase.invoke("")
        //Then
        coVerify (exactly = 1){ repositoryMockk.getAlumnoByCurso(any()) }
        assert(result.isEmpty() )
    }
}