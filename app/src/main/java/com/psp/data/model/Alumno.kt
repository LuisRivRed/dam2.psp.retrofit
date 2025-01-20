package com.psp.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class Curso {
    DAM1, DAM2, DAW1, DAW2
}

@Serializable
enum class Asignatura {
    DDI, PSP, AAD, PMDM, EIE
}

@Serializable
data class Alumno(
    var id: Int,
    val nombre: String,
    val fechaNacimiento: String,
    val curso: Curso,
    val email: String,
    val asignaturas: List<Asignatura>
)
