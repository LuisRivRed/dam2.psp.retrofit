package com.api.model

import kotlinx.serialization.Serializable

enum class Denominacion{
    D1, D2, D3, D4, D5, D6, D7, D8, D9
}

enum class Pabellon{
    A, B, C, D
}

enum class Curso{
    DAM1, DAM2, DAW1, DAW2, ASMR1, ASMR2
}

@Serializable
data class Aula(
    var id: String,
    val denominacion: Denominacion,
    val piso: Int,
    val pabellon: Pabellon,
    val curso: Curso
)