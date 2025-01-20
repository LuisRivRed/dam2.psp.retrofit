package com.psp.retrofit

import com.psp.domain.AlumnosApi
import com.psp.domain.model.Alumno
import com.psp.domain.model.Asignatura
import com.psp.domain.model.Curso
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AlumnosServiceTest {

    @Mock
    private lateinit var alumnosService: AlumnosService
    private lateinit var alumnosApi: AlumnosApi

    @Before
    fun setup() {
        alumnosApi = alumnosService.alumnosApi
    }

    @Test
    fun `getAlumnos return succes with list of alumnos`() {
        runTest {
            val alumnos = listOf(
                Alumno(
                    id = 1,
                    nombre = "Juan",
                    fechaNacimiento = "01/01/2000",
                    curso = Curso.DAM1,
                    email = "a@gmail.com",
                    asignaturas = listOf(Asignatura.PMDM, Asignatura.SGE)
                ),
                Alumno(
                    id = 2,
                    nombre = "Ana",
                    fechaNacimiento = "02/02/2000",
                    curso = Curso.DAM2,
                    email = "b@gmail.com",
                    asignaturas = listOf(Asignatura.PSP, Asignatura.PSP)
                ),
                Alumno(
                    id = 3,
                    nombre = "Luis",
                    fechaNacimiento = "03/03/2000",
                    curso = Curso.DAW1,
                    email = "c@gmail.com",
                    asignaturas = listOf(Asignatura.PSP, Asignatura.PSP)
                ),
                Alumno(
                    id = 4,
                    nombre = "Sofía",
                    fechaNacimiento = "04/04/2000",
                    curso = Curso.DAW2,
                    email = "d@gmail.com",
                    asignaturas = listOf(Asignatura.PSP, Asignatura.PSP)
                )
            )
            // whenever(alumnosService.getAlumnos()).thenReturn((alumnos))

            val result = alumnosService.getAlumnos()

            assert(result == alumnos)
        }
    }



}