package com.psp.retrofit

import com.psp.domain.AlumnosApi
import com.psp.domain.model.Alumno
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
                     id = 0,
                     nombre = "Rubén",
                     fechaNacimiento = "01/01/1999",
                     curso = Curso.DAM1,
                     email = "william.henry.harrison@example-pet-store.com",
                     asignaturas = emptyList()
                 ),
                 Alumno(
                     id = 0,
                     nombre = "Rubén",
                     fechaNacimiento = "01/01/1999",
                     curso = Curso.DAM1,
                     email = "james.wilson@example-pet-store.com",
                     asignaturas = emptyList()
                 )
             )
            // whenever(alumnosService.getAlumnos()).thenReturn((alumnos))

            val result = alumnosService.getAlumnos()

            assert(result == alumnos)
        }
    }



}