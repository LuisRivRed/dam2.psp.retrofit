package com.psp.retrofit.service

import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response


class AlumnoRepositoryTest{

    private val apiService: ApiService = mockk()
    private lateinit var repository: AlumnoRepository

    @Before
    fun setUp() {
        repository = AlumnoRepository(apiService)
    }
    // Get Alumnos
    @Test
    fun `when getAlumnos is called then it returns a list of alumnos`()= runBlocking {
        val listaAlumnos = listOf(
            Alumno(1,
                "Juan",
                "2000-01-01",
                Curso.DAM1,
                "james.wilson@example-pet-store.com",
                listOf(
                    Asignatura.PSP,
                    Asignatura.PMDM)),
            Alumno(2,
                "Pedro",
                "2000-01-01",
                Curso.DAM1,
                "william.orville.douglas@example-pet-store.com",
                listOf(
                    Asignatura.PSP,
                    Asignatura.PMDM)))

        coEvery { apiService.requestAlumnos() } returns Response.success(listaAlumnos)

        val alumnos = repository.getAlumnos()

        assert(alumnos.isNotEmpty())
        assert(alumnos.size == 2)
        assert(alumnos[0].nombre == "Juan")
        assert(alumnos[1].nombre == "Pedro")

    }
    @Test
    fun `when getAlumnos is called then it returns an empty list`()= runBlocking {
        coEvery { apiService.requestAlumnos() } returns Response.success(emptyList())

        val alumnos = repository.getAlumnos()

        assert(alumnos.isEmpty())
    }

    // Get Alumno
    @Test
    fun `when getAlumno is called then it returns a alumno`()= runBlocking {
        val alumno = Alumno(
            1,
            "Juan",
            "2000-01-01",
            Curso.DAM1,
            "james.madison@examplepetstore.com",
            listOf(
                Asignatura.PSP,
                Asignatura.PMDM))
        coEvery { apiService.requestAlumno(1) } returns Response.success(alumno)

        val alumnoObtenido = repository.getAlumno(1)

        assert(alumnoObtenido != null)
        assert(alumnoObtenido?.nombre == "Juan")

    }
    @Test
    fun `when getAlumno is called then it returns null`()= runBlocking {
        // Crear un cuerpo vacío para el error
        val errorResponseBody = ResponseBody.create(MediaType.parse("application/json"), "{}")

        coEvery { apiService.requestAlumno(1) } returns Response.error(404, errorResponseBody)

        val alumnoObtenido = repository.getAlumno(1)

        assert(alumnoObtenido == null)
    }

    // Add Alumno
    @Test
    fun `when addAlumno is called then it returns a alumno`()= runBlocking {
        val alumno = Alumno(
            1,
            "Juan",
            "2000-01-01",
            Curso.DAW1,
            "william.henry.harrison@example-pet-store.com",
            listOf(
                Asignatura.PSP,
                Asignatura.PMDM))
        coEvery { apiService.createAlumno(alumno) } returns Response.success(alumno)

        val alumnoObtenido = repository.addAlumno(alumno)

        assert(alumnoObtenido != null)
        assert(alumnoObtenido?.nombre == "Juan")
    }
    @Test
    fun `when addAlumno is called then it returns null`()= runBlocking {
        val errorResponseBody = ResponseBody.create(MediaType.parse("application/json"), "{}")
        coEvery { apiService.createAlumno(any()) } returns Response.error(404, errorResponseBody)

        val alumnoObtenido = repository.addAlumno(Alumno(
            1,
            "Juan",
            "2000-01-01",
            Curso.DAW1,
            "william.a.wheeler@example-pet-store.com",
            listOf(
                Asignatura.PSP,
                Asignatura.PMDM)))

        assert(alumnoObtenido == null)
    }


    // Delete Alumno
    @Test
    fun `when deleteAlumno is called then it returns a boolean`()= runBlocking {

        coEvery { apiService.deleteAlumno(1) } returns Response.success(null)

        val alumnoObtenido = repository.deleteAlumno(1)

        assert(alumnoObtenido)

    }
    @Test
    fun `when deleteAlumno is called then it returns false`()= runBlocking {
        val errorResponseBody = ResponseBody.create(MediaType.parse("application/json"), "{}")
        coEvery { apiService.deleteAlumno(1) } returns Response.error(404, errorResponseBody)

        val alumnoObtenido = repository.deleteAlumno(1)

        assert(!alumnoObtenido)
    }

}