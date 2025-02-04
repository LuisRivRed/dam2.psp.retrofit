package com.psp.model

import kotlinx.serialization.Serializable

@Serializable
class LoginRequest(
    val username: String = "",
    val password: String = "",
)