package com.psp.data

data class LoginRequest(
    val username: String,
    val password: String
)
data class TokenResponse(
    val token: String
)
