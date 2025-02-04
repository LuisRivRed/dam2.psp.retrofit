package com.psp.retrofit

import com.psp.retrofit.data.remote.ApiService
import com.psp.retrofit.model.StudentRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class ClientTest {
    @Mock
    private lateinit var apiService: ApiService

    private lateinit var repository: StudentRepository

    @Before
    fun setup() {
        repository = StudentRepository(apiService)
    }

    @Test
    fun `login success returns token`() = runTest {
        val token = "test_token"
        whenever(apiService.login(any())).thenReturn(
            Response.success(TokenResponse(token))
        )

        val result = repository.login("admin", "password")

        assertTrue(result.isSuccess)
        assertEquals(token, result.getOrNull())
    }
}