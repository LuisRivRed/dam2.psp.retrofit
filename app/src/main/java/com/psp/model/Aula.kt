package com.psp.model

import kotlinx.serialization.Serializable

@Serializable
data class Aula(
    var id: Int,
    val nombre: String,
    val capacidad: Int
)