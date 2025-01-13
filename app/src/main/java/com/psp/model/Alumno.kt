package com.psp.model

import kotlinx.serialization.Serializable


enum class Curso {
    DAM1, DAM2, DAW1, DAW2
}

enum class Asignatura {
    EIE, PSP, AAD, PMDM, DDI
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
