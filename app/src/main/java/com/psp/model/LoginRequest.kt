package com.psp.model

data class LoginRequest(
    val username: String,
    val password: String
)

data class TokenResponse(
    val token: String
)