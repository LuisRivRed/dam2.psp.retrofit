package com.psp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable



data class Alumno(

    @SerializedName("id")var id: Int,
    @SerializedName("nombre")val nombre: String,
    @SerializedName("fechaNacimiento")val fechaNacimiento: String,
    @SerializedName("curso")val curso: Curso,
    @SerializedName("email")val email: String,
    @SerializedName("asignaturas")val asignaturas: List<Asignatura>
)

enum class Curso {
    DAM1, DAM2, DAW1, DAW2
}

enum class Asignatura {
    EIE, PSP, AAD, PMDM, DDI
}


