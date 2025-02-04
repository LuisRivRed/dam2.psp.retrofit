package com.psp.model.usecases

import com.psp.model.Alumno
import com.psp.model.AlumnoRepository
import com.psp.model.LoginRequest
import com.psp.model.TokenResponse

class LoginUseCase (val repository : AlumnoRepository) {

    suspend fun login(login: LoginRequest): Result<TokenResponse> {
        return repository.login(login)
    }
}