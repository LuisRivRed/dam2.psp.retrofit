package com.psp.retrofit.data.remote.api

import com.psp.data.remote.api.ApiService
import com.psp.model.Alumno
import com.psp.model.AlumnoRepository
import com.psp.model.Curso
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {

    @Mock
    private lateinit var apiService: ApiService
    private lateinit var repository: AlumnoRepository

    @Before
    fun setup() {
    }

    @Test
    fun `getAlumnos test`() = runTest {
        val alumnos = listOf(Alumno(1, "Pepe", "Pérez", Curso.DAM2, "", emptyList()))
        whenever(apiService.fetchAlumnos()).thenReturn(alumnos)

        val result = apiService.fetchAlumnos()

        assert(result == alumnos)
    }


}