package com.psp.retrofit.service

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {

    @Mock
    private lateinit var apiService: ApiService
    private lateinit var repository: AlumnoRepository

    @Before
    fun setUp() {
        repository = AlumnoRepository(apiService)
    }

}