package com.psp.model

enum class Curso { DAW1, DAW2 }

enum class Asignatura {EIE, PSP, PMDM }

data class Alumno(
    var id: Int,
    val nombre: String,
    val fechaNacimiento: String,
    val curso: Curso,
    val email: String,
    val asignaturas: List<Asignatura>
)
