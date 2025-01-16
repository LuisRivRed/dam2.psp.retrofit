package com.psp.retrofit

import com.psp.data.AlumnoApiClient
import com.psp.data.AlumnoApiService
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AlumnoApiServiceTest {
    @Mock
    private lateinit var  apiService:AlumnoApiService
    private lateinit var apiClient: AlumnoApiClient

    @Before
    fun setub(){
        apiClient = AlumnoApiClient()
        apiService= apiClient.apiService
    }

    @Test
    fun `llamo a getAlumnos y me devuelve una lista`()=
        runTest{
            //lo que esperamos que devuelva
            val alumnosPrueba = listOf(
                Alumno(
                    1, "Juan", "10/04/2003", Curso.DAM1,
                    "juan@example.com", listOf<Asignatura>(Asignatura.AAD, Asignatura.EIE, Asignatura.PSP)
                ),
                Alumno(
                    2, "Kai", "04/10/2000", Curso.DAM2,
                    "kai@example.com", listOf<Asignatura>(Asignatura.AAD, Asignatura.PMDM)
                ),
                Alumno(
                    3, "Alicia", "29/07/2002", Curso.DAM1,
                    "alicia@example.com", listOf<Asignatura>(Asignatura.DDI, Asignatura.PSP)
                )
            )

            whenever(apiService.getAlumnos()).thenReturn(
               alumnosPrueba
            )

            val resultado = apiService.getAlumnos()

            // se compara
            assertTrue(alumnosPrueba == resultado)
            assertEquals(alumnosPrueba, resultado.getOrNull(1))

        }

}