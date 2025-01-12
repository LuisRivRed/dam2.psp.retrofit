package com.psp.model

import retrofit2.Response

interface StudentRepository {
    suspend fun getStudents(): Response<List<Student>>
    suspend fun getStudentById(id: Int): Response<Student>
    suspend fun getStudentByName(name: String): Response<Student>
    suspend fun getStudentByCourse(course: Course): Response<List<Student>>
    suspend fun newStudent(student: Student): Response<Student>
    suspend fun deleteStudent(id: Int): Response<Boolean>
}