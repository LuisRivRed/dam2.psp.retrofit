package com.psp.model

import kotlinx.serialization.Serializable

enum class Curso {
    DAM1, DAM2, DAW1, DAW2
}

enum class Asignatura {
    PSP, PMDM, DDI, AAD, SGE
}

@Serializable
data class Alumno(
    var id: Int,
    val nombre: String,
    val fechaNacimiento: String,
    val curso: Curso,
    val email: String,
    val asignatura: List<Asignatura>
)

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)

@Serializable
data class TokenResponse(
    val token: String
)
