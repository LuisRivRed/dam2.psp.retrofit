package com.psp.kotlin

import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso

class AlumnoRepository( private val apiService: ApiService){
    suspend fun getAlumnos() : List<Alumno> {
        return listOf(
            Alumno(
                1,
                "Juan",
                "1999-01-01",
                Curso.DAM1,
                "juanEmail@gmail.com",
                listOf(Asignatura.PSP, Asignatura.SGE)
            ),
            Alumno(
                2,
                "Pedro",
                "1998-01-01",
                Curso.DAM2,
                "pedroEmail@mail.com",
                listOf(Asignatura.AAD, Asignatura.DDI)
            ),
            Alumno(3, "Ana", "1997-01-01", Curso.DAW1, "email3", listOf(Asignatura.PMDM)),
            Alumno(
                4,
                "Maria",
                "1996-01-01",
                Curso.DAW2,
                "email4",
                listOf(Asignatura.PSP, Asignatura.SGE, Asignatura.AAD)
            )
        )

    }

}