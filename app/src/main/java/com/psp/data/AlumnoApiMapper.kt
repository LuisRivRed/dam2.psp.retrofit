package com.psp.data

import com.psp.model.Alumno

fun AlumnoApiModel.toModel(): Alumno {

    return Alumno(
        id = this.id.toInt(),
        nombre = this.nombre,
        fechaNacimiento = this.fechaNacimiento,
        curso = this.curso,
        email = this.email,
        asignaturas = this.asignaturas,
    )
}

