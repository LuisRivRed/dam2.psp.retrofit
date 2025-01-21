package com.psp.data

import com.psp.model.Curso
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import org.testng.Assert.assertEquals
import retrofit2.Response


    @RunWith(MockitoJUnitRunner::class)
    class AlumnoServiceTest {

        @Mock
        private lateinit var service: AlumnoService

        @Before
        fun setup() {
            MockitoAnnotations.openMocks(this)
        }

        @Test
        fun testGetAlumnosByCursoSuccess() = runBlocking {
            // Datos falsos del servidor
            val mockApiModels = listOf(
                AlumnoApiModel(
                    id = "1",
                    nombre = "Pedro",
                    fechaNacimiento = "2000-06-05",
                    curso = Curso.DAM1,
                    email = "pedro@educa.jcyl.es",
                    asignaturas = emptyList()
                )
            )

            // Simulamos la respuesta del servidor
            whenever(service.getAlumnosByCurso("DAM1")).thenReturn(Response.success(mockApiModels))

            // Llamamos al método del servicio
            val response = service.getAlumnosByCurso("DAM1")

            // Comprobamos el resultado
            assertTrue(response.isSuccessful)
            assertEquals(mockApiModels, response.body())
        }

        @Test
        fun testGetAlumnoByNombreSuccess() = runBlocking {
            // Creamos el alumno de prueba
            val mockAlumno = AlumnoApiModel(
                id = "1",
                nombre = "Pedro",
                fechaNacimiento = "2000-06-05",
                curso = Curso.DAM1,
                email = "pedro@educa.jcyl.es",
                asignaturas = emptyList()
            )

            // Simulamos la respuesta del servidor
            whenever(service.getAlumnoByNombre("Pedro")).thenReturn(Response.success(mockAlumno))

            // Llamamos al método del servicio
            val response = service.getAlumnoByNombre("Pedro")

            // Comprobamos el resultado
            assertTrue(response.isSuccessful)
            assertEquals(mockAlumno, response.body())
        }

        @Test
        fun testAddAlumnoSuccess() = runBlocking{

            val mockAlumno = AlumnoApiModel(
                id = "1",
                nombre = "Pedro",
                fechaNacimiento = "2000-06-05",
                curso = Curso.DAM1,
                email = "pedro@educa.jcyl.es",
                asignaturas = emptyList()
            )

            whenever(service.addAlumno(mockAlumno)).thenReturn(Response.success(mockAlumno))

            val response = service.addAlumno(mockAlumno)

            assertTrue(response.isSuccessful)
            assertEquals(mockAlumno, response.body())
        }

        @Test
        fun testEliminarAlumnoSuccess() = runBlocking {

            whenever(service.eliminarAlumnoDelete(1)).thenReturn(Response.success("Alumno eliminado con éxito"))

            val response = service.eliminarAlumnoDelete(1)

            assertTrue(response.isSuccessful)
            assertEquals("Alumno eliminado con éxito", response.body())
        }
    }

