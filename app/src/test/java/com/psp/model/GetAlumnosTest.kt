package com.psp.model

import com.psp.data.AlumnoDataRepository
import com.psp.data.model.Alumno
import com.psp.data.model.Asignatura
import com.psp.data.model.Curso
import com.psp.data.remote.ApiService
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class GetAlumnosTest {
    @Mock
    private lateinit var apiService: ApiService
    private lateinit var dataRepository: AlumnoDataRepository

    val mockAlumnos = listOf(
        Alumno(
            id = 1,
            curso = Curso.DAM1,
            email = "juancarlos33@gmail.com",
            nombre = "juan carlos",
            asignaturas = listOf(Asignatura.AAD,Asignatura.PMDM),
            fechaNacimiento = "12121"
        ),
        Alumno(
            id = 2,
            curso = Curso.DAM2,
            email = "devian@gmail.com",
            nombre = "deivi",
            asignaturas = listOf(Asignatura.AAD,Asignatura.PMDM,Asignatura.PSP),
            fechaNacimiento = "12121234434"
        )
    )

    @Before
    fun setup(){
        dataRepository = AlumnoDataRepository(apiService)
    }

    //Obtener todos los alumnos (éxito)
    @Test
    fun `getAlumnos returns list of students`() = runTest {
        whenever(apiService.getAlumnos()).thenReturn(Response.success(mockAlumnos))

        val response = dataRepository.getAlumnos()

        Assert.assertTrue(response.isSuccess)
        Assert.assertEquals(mockAlumnos, response.getOrNull())
    }

    //Obtener todos los alumnos (error)
    @Test
    fun `getAlumnos returns error`() = runTest {
        whenever(apiService.getAlumnos()).thenReturn(Response.error(500, ResponseBody.create(null, "Internal Server Error")))

        val response = dataRepository.getAlumnos()

        Assert.assertFalse(response.isFailure)
    }

    //Obtener alumno por nombre (éxito)
    @Test
    fun `getAlumnoByName returns student`() = runTest {
        whenever(apiService.getAlumnosByName("Juan Carlos")).thenReturn(Response.success(mockAlumnos[0]))

        val response = dataRepository.getAlumnosByName("Juan Carlos")

        Assert.assertTrue(response.isSuccessful)
        Assert.assertEquals(mockAlumnos[0], response.body())
    }

    //Obtener alumno por nombre (no encontrado)
    @Test
    fun `getAlumnoByName returns not found`() = runTest {
        whenever(apiService.getAlumnosByName("Desconocido")).thenReturn(Response.error(404, ResponseBody.create(null, "Not Found")))

        val response = dataRepository.getAlumnosByName("Desconocido")

        Assert.assertFalse(response.isSuccessful)
    }

    //Obtener alumno por ID (éxito)
    @Test
    fun `getAlumnoById returns student`() = runTest {
        whenever(apiService.getAlumnoById(1)).thenReturn(Response.success(mockAlumnos[0]))

        val response = dataRepository.getAlumnoById(1)

        Assert.assertTrue(response.isSuccessful)
        Assert.assertEquals(mockAlumnos[0], response.body())
    }

    //Obtener alumno por ID (no encontrado)
    @Test
    fun `getAlumnoById returns not found`() = runTest {
        whenever(apiService.getAlumnoById(99)).thenReturn(Response.error(404, ResponseBody.create(null, "Not Found")))

        val response = dataRepository.getAlumnoById(99)

        Assert.assertFalse(response.isSuccessful)
    }

    //Obtener alumnos por curso (éxito)
    @Test
    fun `getAlumnosByCurso returns students`() = runTest {
        whenever(apiService.getAlumnosByCurso(Curso.DAM1.toString())).thenReturn(Response.success(listOf(mockAlumnos[0])))

        val response = dataRepository.getAlumnoByCurso(Curso.DAM1.toString())

        Assert.assertTrue(response.isSuccessful)
        Assert.assertEquals(listOf(mockAlumnos[0]), response.body())
    }

    //Obtener alumnos por curso (vacío)
    @Test
    fun `getAlumnosByCurso returns empty list`() = runTest {
        whenever(apiService.getAlumnosByCurso(Curso.DAW2.toString())).thenReturn(Response.success(emptyList()))

        val response = dataRepository.getAlumnoByCurso(Curso.DAW2.toString())

        Assert.assertTrue(response.isSuccessful)
        Assert.assertTrue(response.body()?.isEmpty() == true)
    }

    //Añadir un alumno (éxito)
    @Test
    fun `addAlumno successfully adds student`() = runTest {
        val newAlumno = Alumno(3, "Pedro", "10/05/2002", Curso.DAM1, "pedro@gmail.com", listOf(Asignatura.EIE))
        whenever(apiService.addAlumno(newAlumno)).thenReturn(Response.success(newAlumno))

        val response = dataRepository.addAlumno(newAlumno)

        Assert.assertTrue(response.isSuccessful)
        Assert.assertEquals(newAlumno, response.body())
    }

    //Añadir un alumno (error)
    @Test
    fun `addAlumno fails to add student`() = runTest {
        val newAlumno = Alumno(3, "Pedro", "10/05/2002", Curso.DAM1, "pedro@gmail.com", listOf(Asignatura.EIE))
        whenever(apiService.addAlumno(newAlumno)).thenReturn(Response.error(400, ResponseBody.create(null, "Bad Request")))

        val response = dataRepository.addAlumno(newAlumno)

        Assert.assertFalse(response.isSuccessful)
    }

    //Borrar un alumno (éxito)
    @Test
    fun `deleteAlumno successfully deletes student`() = runTest {
        whenever(apiService.deleteAlumno(1)).thenReturn(Response.success(Unit))

        val response = dataRepository.deleteAlumno(1)

        Assert.assertTrue(response.isSuccessful)
    }

    //Borrar un alumno (no encontrado)
    @Test
    fun `deleteAlumno returns not found`() = runTest {
        whenever(apiService.deleteAlumno(99)).thenReturn(Response.error(404, ResponseBody.create(null, "Not Found")))

        val response = dataRepository.deleteAlumno(99)

        Assert.assertFalse(response.isSuccessful)
    }
}