package com.psp.retrofit

import com.psp.data.StudentDataRepository
import com.psp.data.remote.ApiService
import com.psp.model.Course
import com.psp.model.Student
import com.psp.model.Subject
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class StudentDataRepositoryTest {
    @Mock
    private lateinit var apiService: ApiService
    private lateinit var studentDataRepository: StudentDataRepository

    @Before
    fun setUp() {
        studentDataRepository = StudentDataRepository(apiService)
    }

    @Test
    fun `getStudents returns list of students`() = runTest {
        // Lo que esperamos que devuelva
        val mockStudents = listOf(
            Student(
                1,
                "Pedro",
                "05/06/2000",
                Course.DAM1,
                "pedro@educa.jcyl.es",
                listOf(Subject.AAD, Subject.PMDM, Subject.EIE)
            ),
            Student(
                2,
                "Pepe",
                "12/02/2001",
                Course.DAM2,
                "pepe@educa.jcyl.es",
                listOf(Subject.AAD, Subject.PSP)
            ),
            Student(
                3,
                "Juan",
                "15/09/2002",
                Course.DAW1,
                "juan@educa.jcuyl.es",
                listOf(Subject.PSP)
            )
        )


        whenever(apiService.getStudents()).thenReturn(
            Response.success(mockStudents)
        )

        val response = studentDataRepository.getStudents()

        // Se compara
        assertEquals(200, response.code())
        assertEquals(mockStudents, response.body())
        verify(apiService).getStudents()
    }

    @Test
    fun `getStudentById returns student`() = runTest {
        //Given
        val mockStudent = Student(
            1,
            "Pedro",
            "05/06/2000",
            Course.DAM1,
            "pedro@educa.jcyl.es",
            listOf(Subject.AAD, Subject.PMDM, Subject.EIE)
        )
        whenever(apiService.getStudentById(1)).thenReturn(Response.success(mockStudent))

        //When
        val response = studentDataRepository.getStudentById(1)

        //Then
        assertEquals(200, response.code())
        assertEquals(mockStudent, response.body())
        verify(apiService).getStudentById(1)
    }

    @Test
    fun `test getStudentByName returns student`() = runTest {
        //Given
        val mockStudent = Student(
            1,
            "Pedro",
            "05/06/2000",
            Course.DAM1,
            "pedro@educa.jcyl.es",
            listOf(Subject.AAD, Subject.PMDM, Subject.EIE)
        )
        whenever(apiService.getStudentByName("John")).thenReturn(Response.success(mockStudent))

        //When
        val response = studentDataRepository.getStudentByName("John")

        //Then
        assertEquals(200, response.code())
        assertEquals(mockStudent, response.body())
        verify(apiService).getStudentByName("John")
    }

    @Test
    fun `test getStudentByCourse returns list of students`() = runTest {
        //Given
        val mockStudents = listOf(Student(
            1,
            "Pedro",
            "05/06/2000",
            Course.DAM1,
            "pedro@educa.jcyl.es",
            listOf(Subject.AAD, Subject.PMDM, Subject.EIE)
        ),
            Student(
                2,
                "Pepe",
                "12/02/2001",
                Course.DAM2,
                "pepe@educa.jcyl.es",
                listOf(Subject.AAD, Subject.PSP)
            ),
            Student(
                3,
                "Juan",
                "15/09/2002",
                Course.DAW1,
                "juan@educa.jcuyl.es",
                listOf(Subject.PSP)
            ))
        val mockCourse = Course.DAM1
        whenever(apiService.getStudentByCourse(mockCourse)).thenReturn(Response.success(mockStudents))

        //When
        val response = studentDataRepository.getStudentByCourse(mockCourse)

        //Then
        assertEquals(200, response.code())
        assertEquals(mockStudents, response.body())
        verify(apiService).getStudentByCourse(mockCourse)
    }

    @Test
    fun `test newStudent adds a student`() = runTest {
        //Given
        val mockStudent = Student(
            1,
            "Pedro",
            "05/06/2000",
            Course.DAM1,
            "pedro@educa.jcyl.es",
            listOf(Subject.AAD, Subject.PMDM, Subject.EIE)
        )
        whenever(apiService.newStudent(mockStudent)).thenReturn(Response.success(mockStudent))

        //When
        val response = studentDataRepository.newStudent(mockStudent)

        //Then
        assertEquals(200, response.code())
        assertEquals(mockStudent, response.body())
        verify(apiService).newStudent(mockStudent)
    }

    @Test
    fun `test deleteStudent deletes a student`() = runTest {
        //Given
        whenever(apiService.deleteStudent(1)).thenReturn(Response.success(true))

        //When
        val response = studentDataRepository.deleteStudent(1)

        //Then
        assertEquals(200, response.code())
        assertEquals(true, response.body())
        verify(apiService).deleteStudent(1)
    }
}