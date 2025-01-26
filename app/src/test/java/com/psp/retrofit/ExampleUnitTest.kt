package com.psp.retrofit

import com.psp.data.StudentDataRepository
import com.psp.data.remote.ApiService
import com.psp.model.Course
import com.psp.model.Student
import com.psp.model.Subject
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class) // Configuración del runner para Mockito
class StudentDataRepositoryTest {

    @Mock
    private lateinit var apiService: ApiService // Mock para el servicio remoto
    private lateinit var studentDataRepository: StudentDataRepository // Repositorio a probar

    // Datos simulados centralizados
    companion object MockData {
        val students = listOf(
            Student(
                id = 1,
                name = "alumn1",
                dateBirth = "11/11/1111",
                course = Course.DAM1,
                email = "alumn@email",
                subject = listOf(Subject.AAD, Subject.PMDM, Subject.EIE)
            ),
            Student(
                id = 2,
                name = "alumn2",
                dateBirth = "22/22/2222",
                course = Course.DAM2,
                email = "alumn2@email",
                subject = listOf(Subject.AAD, Subject.PSP)
            )
        )
        val singleStudent = students[0] // Estudiante individual para pruebas
    }

    @Before
    fun setUp() {
        studentDataRepository = StudentDataRepository(apiService) // Inicializar repositorio con el mock
    }

    @Test
    fun `getStudents returns expected list`() = runTest {
        // Configurar mock para devolver la lista de estudiantes
        whenever(apiService.getStudents()).thenReturn(Response.success(MockData.students))

        // Llamada al repositorio
        val response = studentDataRepository.getStudents()

        // Verificaciones
        assertEquals(200, response.code())
        assertEquals(MockData.students, response.body())
        verify(apiService).getStudents() // Verificar llamada al servicio remoto
    }

    @Test
    fun `getStudentById returns correct student`() = runTest {
        // Configurar mock para devolver un estudiante
        whenever(apiService.getStudentById(1)).thenReturn(Response.success(MockData.singleStudent))

        // Llamada al repositorio
        val response = studentDataRepository.getStudentById(1)

        // Verificaciones
        assertEquals(200, response.code())
        assertEquals(MockData.singleStudent, response.body())
        verify(apiService).getStudentById(1)
    }

    @Test
    fun `getStudentByName returns correct student`() = runTest {
        // Configurar mock para devolver un estudiante por nombre
        whenever(apiService.getStudentByName("alumn1")).thenReturn(Response.success(MockData.singleStudent))

        // Llamada al repositorio
        val response = studentDataRepository.getStudentByName("alumn1")

        // Verificaciones
        assertEquals(200, response.code())
        assertEquals(MockData.singleStudent, response.body())
        verify(apiService).getStudentByName("alumn1")
    }

    @Test
    fun `getStudentByCourse returns students of course`() = runTest {
        // Configurar mock para devolver estudiantes de un curso
        whenever(apiService.getStudentByCourse(Course.DAM1)).thenReturn(Response.success(MockData.students))

        // Llamada al repositorio
        val response = studentDataRepository.getStudentByCourse(Course.DAM1)

        // Verificaciones
        assertEquals(200, response.code())
        assertEquals(MockData.students, response.body())
        verify(apiService).getStudentByCourse(Course.DAM1)
    }

    @Test
    fun `newStudent adds student successfully`() = runTest {
        // Configurar mock para agregar un estudiante
        whenever(apiService.newStudent(MockData.singleStudent)).thenReturn(Response.success(MockData.singleStudent))

        // Llamada al repositorio
        val response = studentDataRepository.newStudent(MockData.singleStudent)

        // Verificaciones
        assertEquals(200, response.code())
        assertEquals(MockData.singleStudent, response.body())
        verify(apiService).newStudent(MockData.singleStudent)
    }

    @Test
    fun `deleteStudent removes student successfully`() = runTest {
        // Configurar mock para simular eliminación exitosa
        whenever(apiService.deleteStudent(1)).thenReturn(Response.success(true))

        // Llamada al repositorio
        val response = studentDataRepository.deleteStudent(1)

        // Verificaciones
        assertEquals(200, response.code())
        assertEquals(true, response.body())
        verify(apiService).deleteStudent(1)
    }

    @Test
    fun `handle empty response gracefully`() = runTest {
        // Configurar mock para devolver respuesta vacía
        whenever(apiService.getStudents()).thenReturn(Response.success(null))

        // Llamada al repositorio
        val response = studentDataRepository.getStudents()

        // Verificaciones
        assertEquals(200, response.code())
        assertNull(response.body()) // Verificar que no hay datos
        verify(apiService).getStudents()
    }
}
