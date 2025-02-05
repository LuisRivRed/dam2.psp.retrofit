package com.psp.retrofit.data.remote.api

import com.psp.data.remote.api.AlumnoApiDataSource
import com.psp.data.remote.api.ApiService
import com.psp.model.Alumno
import com.psp.model.AuthInterceptor
import com.psp.model.Curso
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class AlumnoApiDataSourceTest {

    @Mock
    private lateinit var apiService: ApiService
    @Mock
    private lateinit var authInterceptor: AuthInterceptor
    private lateinit var dataSource: AlumnoApiDataSource
    @Before
    fun setUp() {
        dataSource = AlumnoApiDataSource(apiService, authInterceptor)
    }

    @Test
    fun `when requested, api returns list of alumnos`() = runTest {
        //Given
        val expectedAlumnos = listOf(
            Alumno (1, "Pepe", "Pérez", Curso.DAM2, "educa@email", emptyList())
        )
        val expectedResponse = retrofit2.Response.success(expectedAlumnos)
        whenever(apiService.fetchAlumnos()).thenReturn(expectedResponse)
        //When
        val result = dataSource.getAlumnos()
        //Then
        assert(result?.getOrNull() == expectedAlumnos)


    }
}