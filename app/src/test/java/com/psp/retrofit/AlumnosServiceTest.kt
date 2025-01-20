package com.psp.retrofit

import com.psp.domain.AlumnosApi
import com.psp.domain.model.Alumno
import com.psp.domain.model.Asignatura
import com.psp.domain.model.Curso
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class AlumnosServiceTest {

    @MockK
    private lateinit var alumnosService: AlumnosService

    @MockK
    private lateinit var alumnosApi: AlumnosApi

    @Before
    fun setup() {
        MockKAnnotations.init(this)

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

            val response = Response.success(alumnos)

            coEvery {
                alumnosService.getAlumnos()
            } returns response

            val result = alumnosService.getAlumnos()

            assert(result == response)
            assert(result.body() == alumnos)
        }
    }



}