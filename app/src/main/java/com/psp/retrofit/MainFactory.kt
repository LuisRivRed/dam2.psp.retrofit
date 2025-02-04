package com.psp.retrofit

import com.psp.data.AlumnoDataRepository
import com.psp.data.remote.api.AlumnoApiDataSource
import com.psp.data.remote.api.ApiClient
import com.psp.model.usecases.*

class MainFactory {

    // Crea una instancia de ApiClient
    private val apiClient = ApiClient()

    // Usa el mismo AuthInterceptor de ApiClient
    private val remoteDataSource = AlumnoApiDataSource(apiClient.apiService, apiClient.authInterceptor)
    private val dataRepository = AlumnoDataRepository(remoteDataSource)

    fun createLoginUseCase() = LoginUseCase(dataRepository)

    fun createGetAlumnoUseCase() = GetAlumnosUseCase(dataRepository)

    fun createGetAlumnoByCursoUseCase() = GetAlumnosByCursoUseCase(dataRepository)

    fun createGetAlumnoByNameUseCase() = GetAlumnoByNameUseCase(dataRepository)

    fun createSaveAlumnoUseCase() = SaveAlumnoUseCase(dataRepository)

    fun createDeleteAlumnoByIdUseCase() = DeleteAlumnoByIdUseCase(dataRepository)
}