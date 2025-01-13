package com.example.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

enum class Curso {
    DAM1, DAM2, DAW1, DAW2
}

enum class Subject {
    PMDM, AAD, DDI, PSP, SOM, SER, SI, P, LM
}

@Serializable
data class Alumno(
    @SerializedName("id") var id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("birthDate") val birthDate: String,
    @SerializedName("curso") val curso: Curso,
    @SerializedName("email") val email: String,
    @SerializedName("subjects") val subjects: ArrayList<Subject>
)
