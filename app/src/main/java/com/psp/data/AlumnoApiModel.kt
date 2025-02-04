package com.psp.data

import com.psp.model.Asignatura
import com.psp.model.Curso

data class AlumnoApiModel(
        var id: String,
        val nombre: String,
        val fechaNacimiento: String,
        val curso: Curso,
        val email: String,
        val asignatura: List<Asignatura>
)