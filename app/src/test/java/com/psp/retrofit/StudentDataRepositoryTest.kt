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
    }
}