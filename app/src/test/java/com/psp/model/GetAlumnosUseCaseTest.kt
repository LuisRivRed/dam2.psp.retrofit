package com.psp.model

import com.psp.data.AlumnoDataRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetAlumnosUseCaseTest {
    @RelaxedMockK
    private lateinit var repositoryMock: AlumnoDataRepository

    lateinit var getAlumnosUseCase: GetAlumnosUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getAlumnosUseCase = GetAlumnosUseCase(repositoryMock)
    }

    @Test
    fun deberiaDevolverListaDeAlumnosCuandoRepositorioRetornaDatosDeLaApi() = runBlocking {
        //Given
            val mockList = listOf(
                Alumno(
                    id = 1,
                    nombre = "Juan Pérez",
                    fechaNacimiento = "2005-03-15",
                    curso = Curso.DAM1,
                    email = "juan.perez@example.com",
                    asignaturas = listOf(Asignatura.PSP, Asignatura.AAD, Asignatura.PMDM)
                ),
                Alumno(
                    id = 2,
                    nombre = "María López",
                    fechaNacimiento = "2004-11-22",
                    curso = Curso.DAW2,
                    email = "maria.lopez@example.com",
                    asignaturas = listOf(Asignatura.DDI, Asignatura.SGE, Asignatura.EIE)
                )
            )
        coEvery { repositoryMock.getAlumnos() } returns mockList
        //When
        val respuesta = getAlumnosUseCase.invoke()
        //Them
        coVerify (exactly =1){  repositoryMock.getAlumnos()}
        assert(mockList == respuesta)
    }

    @Test
    fun deberiaRetornarListaVaciaCuandoNoHayAlumnosEnElRepositorio() = runBlocking {
        //Given
        coEvery { repositoryMock.getAlumnos() } returns emptyList()
        //When
        getAlumnosUseCase.invoke()
        //Them
        coVerify (exactly =1){  repositoryMock.getAlumnos()}
    }
}