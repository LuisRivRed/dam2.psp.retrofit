import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import com.psp.api.ApiService
import com.psp.model.Aula
import retrofit2.Response

class AulaRepositoryTest {

    private val apiService = mock(ApiService::class.java)
    private val aulaRepository = AulaRepository(apiService)

    @Test
    fun login_returnsTokenSuccessfully() = runBlocking {
        val expectedToken = "sampleToken"
        `when`(apiService.login(any())).thenReturn(Response.success(TokenResponse(expectedToken)))

        val result = aulaRepository.login("username", "password")
        assertEquals(Result.success(expectedToken), result)
    }

    @Test
    fun login_returnsAuthenticationError() = runBlocking {
        `when`(apiService.login(any())).thenReturn(Response.error(401, mock(ResponseBody::class.java)))

        val result = aulaRepository.login("username", "password")
        assert(result.isFailure)
    }

    @Test
    fun getAulas_returnsListOfAulas() = runBlocking {
        val expectedAulas = listOf(Aula(1, "Aula 1", 30))
        `when`(apiService.getAulas()).thenReturn(Response.success(expectedAulas))

        val result = aulaRepository.getAulas()
        assertEquals(Result.success(expectedAulas), result)
    }

    @Test
    fun getAulas_returnsEmptyListWhenNoAulas() = runBlocking {
        `when`(apiService.getAulas()).thenReturn(Response.success(emptyList()))

        val result = aulaRepository.getAulas()
        assertEquals(Result.success(emptyList<Aula>()), result)
    }

    @Test
    fun getAulas_returnsErrorWhenApiFails() = runBlocking {
        `when`(apiService.getAulas()).thenReturn(Response.error(500, mock(ResponseBody::class.java)))

        val result = aulaRepository.getAulas()
        assert(result.isFailure)
    }
}