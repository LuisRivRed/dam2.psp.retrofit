package com.psp.retrofit

import com.psp.data.AlumnoDataRepository
import com.psp.data.AlumnoService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.Response

class ClientTest {

    @Mock
    private lateinit var alumnoService: AlumnoService
    private lateinit var repository: AlumnoDataRepository

    @Before
    fun setUp() {
        repository = AlumnoDataRepository(alumnoService)
    }
    /*
    @Test
    fun `login success returns token`(): Unit = runTest {
        val token = "test_token"
        whenever(alumnoService.login(any())).thenReturn(
            Response.success(TokenResponse(token))
        )

        val result = repository.login("admin", "password")

        assertTrue(result.isSuccess)
        assertEquals(token, result.getOrNull())
    }
     */
}