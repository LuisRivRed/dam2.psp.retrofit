package com.psp.retrofit

import com.psp.data.AlumnoDataRepository
import com.psp.data.remote.api.AlumnoApiDataSource
import com.psp.data.remote.api.ApiClient
import com.psp.data.remote.api.ApiService
import com.psp.model.usecases.*

class MainFactory {

    private val remoteDataSource = AlumnoApiDataSource(ApiClient().apiService)
    private val dataRepository = AlumnoDataRepository(remoteDataSource)


    fun createGetAlumnoUseCase() = GetAlumnosUseCase(dataRepository)

    fun createGetAlumnoByCursoUseCase() = GetAlumnosByCursoUseCase(dataRepository)

    fun createGetAlumnoByNameUseCase() = GetAlumnoByNameUseCase(dataRepository)

    fun createSaveAlumnoUseCase() = SaveAlumnoUseCase(dataRepository)

    fun createDeleteAlumnoByIdUseCase() = DeleteAlumnoByIdUseCase(dataRepository)


}