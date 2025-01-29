package com.psp.model

enum class Course {
    DAM1, DAM2, DAW1, DAW2
}

enum class Subject {
    EIE, PSP, AAD, PMDM, DDI
}

data class Student(
    var id: Int,
    val name: String,
    val dateBirth: String,
    val course: Course,
    val email: String,
    val subject: List<Subject>
)