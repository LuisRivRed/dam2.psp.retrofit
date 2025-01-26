package com.psp.model

import kotlinx.serialization.Serializable

enum class Course {
    DAM1, DAM2, DAW1, DAW2
}

enum class Subject {
    EIE, PSP, AAD, PMDM, DDI
}

@Serializable
data class Student(
    var id: Int,
    val name: String,
    val dateBirth: String,
    val course: Course,
    val email: String,
    val subject: List<Subject>
)