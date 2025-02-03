package com.psp.retrofit.service

import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import com.psp.model.LoginRequest
import com.psp.model.TokenResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.every
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

class AlumnoRepositoryTest {

    private lateinit var alumnoRepository: AlumnoRepository
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        apiService = mock(ApiService::class.java)
        alumnoRepository = AlumnoRepository(apiService)
    }

    @Test
    fun `test login success`() = runBlocking {
        coEvery { apiService.login(LoginRequest("admin", "password")) } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns TokenResponse("mock-token")
        }

        val result = alumnoRepository.login("admin", "password")

        assertTrue(result.isSuccess)
        assertEquals("mock-token", result.getOrNull())
        coVerify { apiService.login(LoginRequest("admin", "password")) }
    }

}