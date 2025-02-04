package com.psp.data

import com.psp.data.remote.ApiService
import com.psp.model.Course
import com.psp.model.Student
import com.psp.model.StudentRepository
import retrofit2.Response

/**
 * Implementación de StudentRepository que obtiene los datos desde una API remota.
 * Usa ApiService para realizar las llamadas y devuelve los resultados encapsulados en Response.
 */
class StudentDataRepository(private val apiService: ApiService) : StudentRepository {

    /**
     * Obtiene la lista de todos los estudiantes desde la API.
     * @return Response con la lista de estudiantes.
     */
    override suspend fun getStudents(): Response<List<Student>> {
        return Response.success(apiService.getStudents().body())
    }

    /**
     * Obtiene un estudiante por su ID.
     * @param id Identificador del estudiante.
     * @return Response con el estudiante correspondiente.
     */
    override suspend fun getStudentById(id: Int): Response<Student> {
        return Response.success(apiService.getStudentById(id).body())
    }

    /**
     * Obtiene un estudiante por su nombre.
     * @param name Nombre del estudiante.
     * @return Response con el estudiante correspondiente.
     */
    override suspend fun getStudentByName(name: String): Response<Student> {
        return Response.success(apiService.getStudentByName(name).body())
    }

    /**
     * Obtiene una lista de estudiantes que pertenecen a un curso específico.
     * @param course Curso al que pertenecen los estudiantes.
     * @return Response con la lista de estudiantes en ese curso.
     */
    override suspend fun getStudentByCourse(course: Course): Response<List<Student>> {
        return Response.success(apiService.getStudentByCourse(course).body())
    }

    /**
     * Crea un nuevo estudiante en la base de datos.
     * @param student Estudiante a registrar.
     * @return Response con el estudiante creado.
     */
    override suspend fun newStudent(student: Student): Response<Student> {
        return Response.success(apiService.newStudent(student).body())
    }

    /**
     * Elimina un estudiante por su ID.
     * @param id Identificador del estudiante a eliminar.
     * @return Response con un valor booleano indicando si la operación fue exitosa.
     */
    override suspend fun deleteStudent(id: Int): Response<Boolean> {
        return Response.success(apiService.deleteStudent(id).body())
    }
}
