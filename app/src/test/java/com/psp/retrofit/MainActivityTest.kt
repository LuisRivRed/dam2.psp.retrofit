import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import com.psp.api.ApiService
import com.psp.model.Alumno
import com.psp.model.Curso
import com.psp.model.Asignatura
import retrofit2.Response

class MainActivityTest {

    private val apiService = mock(ApiService::class.java)
    private val apiClient = mock(ApiClient::class.java).apply {
        `when`(apiService).thenReturn(apiService)
    }

    @Test
    fun getAlumnos_returnsListOfAlumnos() = runBlocking {
        val expectedAlumnos = listOf(Alumno(1, "Ana", "2000-01-01", Curso.DAM1, "ana@example.com", listOf(Asignatura.PSP)))
        `when`(apiService.getAlumnos()).thenReturn(Response.success(expectedAlumnos))

        val response = apiService.getAlumnos()
        assertEquals(expectedAlumnos, response.body())
    }

    @Test
    fun getAlumnosByCurso_returnsFilteredAlumnos() = runBlocking {
        val expectedAlumnos = listOf(Alumno(2, "Juan", "2001-02-02", Curso.DAM1, "juan@example.com", listOf(Asignatura.PSP)))
        `when`(apiService.getAlumnosByCurso("DAM1")).thenReturn(Response.success(expectedAlumnos))

        val response = apiService.getAlumnosByCurso("DAM1")
        assertEquals(expectedAlumnos, response.body())
    }

    @Test
    fun getAlumnoByNombre_returnsAlumno() = runBlocking {
        val expectedAlumno = Alumno(3, "Ana", "2000-01-01", Curso.DAM1, "ana@example.com", listOf(Asignatura.PSP))
        `when`(apiService.getAlumnoByNombre("Ana")).thenReturn(Response.success(expectedAlumno))

        val response = apiService.getAlumnoByNombre("Ana")
        assertEquals(expectedAlumno, response.body())
    }

    @Test
    fun addAlumno_addsAlumnoSuccessfully() = runBlocking {
        val newAlumno = Alumno(5, "juan", "1999-09-12", Curso.DAW2, "email5@gmail.com", listOf(Asignatura.EIE, Asignatura.PMDM, Asignatura.PSP))
        `when`(apiService.addAlumno(newAlumno)).thenReturn(Response.success(Unit))

        val response = apiService.addAlumno(newAlumno)
        assertEquals(Unit, response.body())
    }

    @Test
    fun deleteAlumno_deletesAlumnoSuccessfully() = runBlocking {
        `when`(apiService.deleteAlumno(1)).thenReturn(Response.success(Unit))

        val response = apiService.deleteAlumno(1)
        assertEquals(Unit, response.body())
    }
}