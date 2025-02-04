package com.psp.retrofit

import com.api.model.Aula
import com.api.model.Asignatura
import com.api.model.Curso
import com.api.model.Denominacion
import com.psp.model.LoginRequest
import com.psp.model.TokenResponse
import com.psp.remote.ApiService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AulaServiceTest {

    @Mock
    private lateinit var apiService: ApiService

    private var tokenPrueba = "test_token"

    private val mockAulas = listOf(
        Aula(
            id = "1",
            curso = Curso.DAM1,
            nombre = "Aula 101",
            denominaciones = (Denominacion.D1)
        ),
        Aula(
            id = "2",
            curso = Curso.DAM2,
            nombre = "Aula 202",
            denominaciones = listOf(Denominacion.D1, Denominacion.D2, Denominacion.D3)
        ),
        Aula(
            id = "3",
            curso = Curso.DAM2,
            nombre = "Aula 303",
            denominaciones = listOf(Denominacion.D1, Denominacion.D2, Denominacion.D3)
        )
    )

    @Before
    fun setup() {
    }

    @Test
    fun getAulas() = runTest {
        whenever(apiService.getAulas(tokenPrueba)).thenReturn(Response.success(mockAulas))

        val response = apiService.getAulas(tokenPrueba)

        Assert.assertTrue(response.isSuccessful)
        assertEquals(mockAulas, response.body())
    }

    @Test
    fun getAulasNull() = runTest {
        val errorResponseBody = ResponseBody.create("application/json".toMediaTypeOrNull(), "Not Found")
        whenever(apiService.getAulas(tokenPrueba)).thenReturn(Response.error(404, errorResponseBody))

        val response = apiService.getAulas(tokenPrueba)

        Assert.assertFalse(response.isSuccessful)
        Assert.assertNull(response.body())
    }

    @Test
    fun getAulaById() = runTest {
        val id = "1"
        whenever(apiService.getAulas(id)).thenReturn(Response.success(mockAulas[1]))

        val response = apiService.getAulas(id)

        Assert.assertTrue(response.isSuccessful)
        assertEquals(mockAulas[1], response.body())
    }

    @Test
    fun deleteAula() = runTest {
        val id = "2"
        whenever(apiService.deleteAula(id)).thenReturn(Response.success(Unit))

        val response = apiService.deleteAula(id)

        Assert.assertTrue(response.isSuccessful)
        assertEquals(Unit, response.body())
    }

    @Test
    fun getAulasEmpty() = runTest {
        whenever(apiService.getAulas(tokenPrueba)).thenReturn(Response.success(emptyList()))

        val response = apiService.getAulas(tokenPrueba)

        Assert.assertTrue(response.isSuccessful)
        Assert.assertTrue(response.body().isNullOrEmpty())
    }

    @Test
    fun getAulaByIdNotFound() = runTest {
        val id = "99"
        val errorResponseBody = ResponseBody.create("application/json".toMediaTypeOrNull(), "Not Found")
        whenever(apiService.getAulas(id)).thenReturn(Response.error(404, errorResponseBody))

        val response = apiService.getAulas(id)

        Assert.assertFalse(response.isSuccessful)
        Assert.assertNull(response.body())
    }

    @Test
    fun deleteAulaNotFound() = runTest {
        val id = "99"
        val errorResponseBody = ResponseBody.create("application/json".toMediaTypeOrNull(), "Internal Server Error")
        whenever(apiService.deleteAula(id)).thenReturn(Response.error(500, errorResponseBody))

        val response = apiService.deleteAula(id)

        Assert.assertFalse(response.isSuccessful)
        Assert.assertNull(response.body())
    }

    @Test
    fun `login success returns token`() = runTest {
        val token = "test_token"
        whenever(apiService.login(any())).thenReturn(Response.success(TokenResponse(token)))

        val response = apiService.login(LoginRequest("admin", "password"))

        assertTrue(response.isSuccessful)
        assertEquals(token, response.body()?.token)
    }
}