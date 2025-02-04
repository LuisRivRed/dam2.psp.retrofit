package com.psp.retrofit


import com.psp.model.LoginRequest
import com.psp.remote.ApiService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class AulaServiceTest {

    private lateinit var apiService: ApiService
    private var tokenPrueba = "test_token"

    @Before
    fun setup() {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080/") // URL del servidor de pruebas
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    @Test
    fun getAulas() = runTest {
        val response = apiService.getAulas(tokenPrueba)

        Assert.assertTrue(response.isSuccessful)
        assertTrue(response.body()?.isNotEmpty() == true)
    }

    @Test
    fun getAulasNull() = runTest {
        val response = apiService.getAulas("invalid_token")

        Assert.assertFalse(response.isSuccessful)
        Assert.assertNull(response.body())
    }

    @Test
    fun getAulaById() = runTest {
        val id = "1"
        val response = apiService.getAulas(id)

        Assert.assertTrue(response.isSuccessful)
        assertTrue(response.body()?.isNotEmpty() == true)
    }

    @Test
    fun deleteAula() = runTest {
        val id = "2"
        val response = apiService.deleteAula(id)

        Assert.assertTrue(response.isSuccessful)
        assertEquals(Unit, response.body())
    }

    @Test
    fun getAulasEmpty() = runTest {
        val response = apiService.getAulas(tokenPrueba)

        Assert.assertTrue(response.isSuccessful)
        Assert.assertTrue(response.body().isNullOrEmpty())
    }

    @Test
    fun getAulaByIdNotFound() = runTest {
        val id = "99"
        val response = apiService.getAulas(id)

        Assert.assertFalse(response.isSuccessful)
        Assert.assertNull(response.body())
    }

    @Test
    fun deleteAulaNotFound() = runTest {
        val id = 99
        val response = apiService.deleteAula(id)

        Assert.assertFalse(response.isSuccessful)
        Assert.assertNull(response.body())
    }

    @Test
    fun `login success returns token`() = runTest {
        val response = apiService.login(LoginRequest("admin", "password"))

        assertTrue(response.isSuccessful)
        assertEquals(tokenPrueba, response.body()?.token)
    }
}