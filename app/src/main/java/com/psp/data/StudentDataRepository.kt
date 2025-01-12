package com.psp.data

import com.psp.data.remote.ApiService
import com.psp.model.Course
import com.psp.model.Student
import com.psp.model.StudentRepository
import retrofit2.Response

class StudentDataRepository(private val apiService: ApiService) : StudentRepository {
    override suspend fun getStudents(): Response<List<Student>> {
        return Response.success(apiService.getStudents().body())
    }

    override suspend fun getStudentById(id: Int): Response<Student> {
        return Response.success(apiService.getStudentById(id).body())
    }

    override suspend fun getStudentByName(name: String): Response<Student> {
        return Response.success(apiService.getStudentByName(name).body())
    }

    override suspend fun getStudentByCourse(course: Course): Response<List<Student>> {
        return Response.success(apiService.getStudentByCourse(course).body())
    }

    override suspend fun newStudent(student: Student): Response<Student> {
        return Response.success(apiService.newStudent(student).body())
    }

    override suspend fun deleteStudent(id: Int): Response<Boolean> {
        return Response.success(apiService.deleteStudent(id).body())
    }
}