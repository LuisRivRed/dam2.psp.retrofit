package com.psp.data

import com.psp.data.remote.api.AlumnoApiDataSource
import com.psp.model.Alumno
import com.psp.model.AlumnoRepository
import com.psp.model.Curso

class AlumnoDataRepository(
    private val remoteDataSource : AlumnoApiDataSource
) : AlumnoRepository {

    override suspend fun getAlumnos() = remoteDataSource.getAlumnos()

    override suspend fun getAlumnosByCurso(curso: Curso) = remoteDataSource.getAlumnosByCurso(curso)

    override suspend fun getAlumnosByName(name: String) = remoteDataSource.getAlumnosByName(name)

    override suspend fun addAlumno(alumno: Alumno) = remoteDataSource.addAlumno(alumno)

    override suspend fun deleteAlumnoById(id: Int) = remoteDataSource.deleteAlumnoById(id)
}