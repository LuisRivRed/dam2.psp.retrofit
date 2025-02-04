package com.psp.retrofit.model

interface StudentRepository {
    suspend fun getStudents(): Result<List<Student>>
    suspend fun getStudentById(id: Int): Result<Student>
    suspend fun getStudentByName(name: String): Result<List<Student>>
    suspend fun getStudentByEmail(email: String): Result<Student>
    suspend fun getStudentsByCourse(course: Course): Result<List<Student>>
    suspend fun getStudentsBySubject(subject: Subject): Result<List<Student>>
    suspend fun addStudent(student: Student): Result<Student>
    suspend fun deleteStudent(id: Int): Result<Boolean>
}