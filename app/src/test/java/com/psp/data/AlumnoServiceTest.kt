package com.psp.data

import com.psp.model.Asignatura
import com.psp.model.Curso
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class AlumnoServiceTest {
    @Mock
    private lateinit var alumnoService: AlumnoService
    private var mockAlumno: AlumnoApiModel = AlumnoApiModel(
        "1",
        "Alejandro",
        "05/06/2001",
        Curso.DAM1,
        "alejandro@gmail.com",
        listOf(
            Asignatura.PSP,
            Asignatura.DDI,
            Asignatura.PMDM
        )
    )
    private val mockListAlumnos: List<AlumnoApiModel> = listOf(
        AlumnoApiModel(
            "1",
            "Alejandro",
            "05/06/2001",
            Curso.DAM1,
            "alejandro@gmail.com",
            listOf(
                Asignatura.PSP,
                Asignatura.DDI,
                Asignatura.PMDM
            )
        ),
        AlumnoApiModel(
            "2",
            "María",
            "12/03/2002",
            Curso.DAM2,
            "maria@gmail.com",
            listOf(
                Asignatura.AAD,
                Asignatura.EIE,
                Asignatura.PSP
            )
        ),
        AlumnoApiModel(
            "3",
            "Luis",
            "20/08/2001",
            Curso.DAW1,
            "luis@gmail.com",
            listOf(
                Asignatura.AAD,
                Asignatura.PMDM,
                Asignatura.PSP
            )
        ),
        AlumnoApiModel(
            "4",
            "Elena",
            "15/11/2003",
            Curso.DAW2,
            "elena@gmail.com",
            listOf(
                Asignatura.PSP,
                Asignatura.PMDM,
                Asignatura.DDI
            )
        )
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    /**
     * Este test verifica que la API devuelve correctamente una lista de alumnos
     * cuando la solicitud a getAlumnos es exitosa.
     */
    @Test
    fun getAlumnos() = runTest {
        val response = Response.success(listOf(mockAlumno))
        whenever(alumnoService.getAlumnos()).thenReturn(response)

        val result = alumnoService.getAlumnos()

        assertTrue(result.isSuccessful)
        assertEquals(listOf(mockAlumno), result.body())
    }

    /**
     * Verifica que si la respuesta a getAlumnos es nula la API aún devuelve una
     * respuesta exitosa, pero con cuerpo nulo.
     */
    @Test
    fun getAlumnosWithNull() = runTest {
        whenever(alumnoService.getAlumnos()).thenReturn(Response.success(null))

        val result = alumnoService.getAlumnos()

        assertTrue(result.isSuccessful)
        assertEquals(null, result.body())
    }

    /**
     * Validamos que la API devuelve los alumnos correspondientes cuando se filtra por un curso
     * específico.
     */
    @Test
    fun getAlumnoByCurso() = runTest {
        val curso = Curso.DAM1
        val response = Response.success(listOf(mockAlumno))
        whenever(alumnoService.getAlumno(curso.toString())).thenReturn(response)

        val result = alumnoService.getAlumno(curso.toString())

        assertTrue(result.isSuccessful)
        assertEquals(listOf(mockAlumno), result.body())
    }

    /**
     * Este verifica que si la respuesta a getAlumno por curso es nula,
     * la API devuelve una respuesta exitosa pero con body null.
     */
    @Test
    fun getAlumnoByCursoWithNull() = runTest {
        val curso = Curso.DAM1
        whenever(alumnoService.getAlumno(curso.toString())).thenReturn(Response.success(null))

        val result = alumnoService.getAlumno(curso.toString())

        assertTrue(result.isSuccessful)
        assertEquals(null, result.body())
    }

    // Validamos que devuelve un alumno cuando se filtra por nombre.
    @Test
    fun getAlumnoByNombre() = runTest {
        val nombre = "Alejandro"
        val response = Response.success(listOf(mockAlumno))
        whenever(alumnoService.getAlumnoPorNombre(nombre)).thenReturn(response)

        val result = alumnoService.getAlumnoPorNombre(nombre)

        assertTrue(result.isSuccessful)
        assertEquals(listOf(mockAlumno), result.body())
    }

    //Validamos que devuelve el body nulo si la respuesta es nula.
    @Test
    fun getAlumnoByNombreWithNull() = runTest {
        val nombre = "Alejandro"
        whenever(alumnoService.getAlumnoPorNombre(nombre)).thenReturn(Response.success(null))

        val result = alumnoService.getAlumnoPorNombre(nombre)

        assertTrue(result.isSuccessful)
        assertEquals(null, result.body())  // Espera que el cuerpo sea nulo
    }

    // Validamos que la API responde de manera correcta cuando añadimos un alumno sin errores.
    @Test
    fun addAlumno() = runTest {
        val response = Response.success(mockAlumno)
        whenever(alumnoService.addAlumno(mockAlumno)).thenReturn(response)

        val result = alumnoService.addAlumno(mockAlumno)

        assertTrue(result.isSuccessful)
        assertEquals(mockAlumno, result.body())
    }

    /**
     * Este test comprueba que, si intentas añadir un alumno con un nombre vacío,
     * la API devuelve un error HTTP 400, ya que en este caso no podía pasar directamente un nulo
     * y encontré esta solución.
     */
    @Test
    fun addAlumnoWithEmptyName() = runTest {
        val alumnoConNombreVacio = mockAlumno.copy(nombre = "")
        whenever(alumnoService.addAlumno(alumnoConNombreVacio))
            .thenReturn(
                Response.error(
                    400,
                    ResponseBody.create(null, "Error: nombre no puede ser vacío")
                )
            )

        val result = alumnoService.addAlumno(alumnoConNombreVacio)

        assertFalse(result.isSuccessful)
        assertEquals(400, result.code())
    }

    //Probamos que la API permite eliminar un alumno exitosamente.
    @Test
    fun deleteAlumno() = runTest {
        val id = "1"
        val response = Response.success(Unit)
        whenever(alumnoService.deleteAlumno(id)).thenReturn(response)

        val result = alumnoService.deleteAlumno(id)

        assertTrue(result.isSuccessful)
        assertEquals(Unit, result.body())
    }

    /**
     * Aquí, similar al de añadido, si pasamos un ID vacío, la API devuelve un error HTTP 400.
     * Ya que no puede borrar un Alumno que no existe.
     */
    @Test
    fun deleteAlumnoWithNullId() = runTest {
        val id: String? = null

        whenever(alumnoService.deleteAlumno(id ?: "")).thenReturn(
            Response.error(
                400,
                ResponseBody.create(null, "Error: ID no válido")
            )
        )
        val result = alumnoService.deleteAlumno(id ?: "")

        assertFalse(result.isSuccessful)
        assertEquals(400, result.code())
    }
}