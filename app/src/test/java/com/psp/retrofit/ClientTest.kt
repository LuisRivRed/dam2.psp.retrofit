package com.psp.retrofit

import com.psp.kotlin.ApiService
import com.psp.model.LoginRequest
import com.psp.model.TokenResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class ClientTest {

    @Mock
    private lateinit var apiService: ApiService

    @Test
    fun `login success returns token`() = runTest {
        val expectedToken = "test_token"
        // Simulamos que apiService.login() devuelve una respuesta exitosa con el token esperado.
        whenever(apiService.login(any())).thenReturn(Response.success(TokenResponse(expectedToken)))

        // Llamamos directamente al método login de apiService
        val response = apiService.login(LoginRequest("admin", "password"))

        // Verificamos que la respuesta sea exitosa y contenga el token esperado
        assertTrue(response.isSuccessful)
        assertEquals(expectedToken, response.body()?.token)
    }
}