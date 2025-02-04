package com.psp.retrofit.model

import retrofit2.Response

interface StudentRepository {
    suspend fun getStudents(): Result<List<Student>>
    suspend fun getStudentById(id: Int): Response<Student>
    suspend fun getStudentByName(name: String): Response<List<Student>>
    suspend fun getStudentByEmail(email: String): Response<Student>
    suspend fun getStudentsByCourse(course: Course): Response<List<Student>>
    suspend fun getStudentsBySubject(subject: Subject): Response<List<Student>>
    suspend fun addStudent(student: Student): Response<Student>
    suspend fun deleteStudent(id: Int): Response<Boolean>
}