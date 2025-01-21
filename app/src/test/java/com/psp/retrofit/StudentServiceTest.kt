import com.example.model.Alumno
import com.example.model.Curso
import com.example.model.Subject
import com.psp.retrofit.api.StudentsService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class StudentServiceTest {

    @Mock
    private lateinit var studentService: StudentsService

    @Test
    fun getStudentsReturnsSuccessWithStudentsList() = runBlocking {

        val students = listOf(
            Alumno(
                id = 1,
                name = "Carlos",
                birthDate = "2003-05-10",
                curso = Curso.DAM1,
                email = "carlos.martinez@example.com",
                subjects = arrayListOf(Subject.PMDM, Subject.AAD, Subject.PSP)
            ),
            Alumno(
                id = 2,
                name = "Lucía Fernández",
                birthDate = "2002-11-22",
                curso = Curso.DAM2,
                email = "lucia.fernandez@example.com",
                subjects = arrayListOf(Subject.DDI, Subject.SOM, Subject.SER)
            ),
            Alumno(
                id = 3,
                name = "Mario López",
                birthDate = "2004-01-15",
                curso = Curso.DAM1,
                email = "mario.lopez@example.com",
                subjects = arrayListOf(Subject.SI, Subject.P, Subject.LM)
            ),
            Alumno(
                id = 4,
                name = "Ana",
                birthDate = "2003-03-30",
                curso = Curso.DAW2,
                email = "ana.garcia@example.com",
                subjects = arrayListOf(Subject.PMDM, Subject.DDI, Subject.SOM)
            ),
            Alumno(
                id = 5,
                name = "Javier Torres",
                birthDate = "2004-07-12",
                curso = Curso.DAW1,
                email = "javier.torres@example.com",
                subjects = arrayListOf(Subject.P, Subject.SOM, Subject.LM)
            ),
            Alumno(
                id = 6,
                name = "Sara Ruiz",
                birthDate = "2003-02-18",
                curso = Curso.DAM2,
                email = "sara.ruiz@example.com",
                subjects = arrayListOf(Subject.DDI, Subject.PMDM, Subject.PSP)
            ),
            Alumno(
                id = 7,
                name = "David Hernández",
                birthDate = "2005-09-25",
                curso = Curso.DAM1,
                email = "david.hernandez@example.com",
                subjects = arrayListOf(Subject.SI, Subject.AAD, Subject.DDI)
            ),
            Alumno(
                id = 8,
                name = "Laura Gómez",
                birthDate = "2004-06-15",
                curso = Curso.DAW2,
                email = "laura.gomez@example.com",
                subjects = arrayListOf(Subject.PMDM, Subject.SER, Subject.P)
            ),
            Alumno(
                id = 9,
                name = "Carlos Ortega",
                birthDate = "2003-10-20",
                curso = Curso.DAM2,
                email = "carlos.ortega@example.com",
                subjects = arrayListOf(Subject.AAD, Subject.SOM, Subject.P)
            ),
            Alumno(
                id = 10,
                name = "Marta Delgado",
                birthDate = "2004-04-05",
                curso = Curso.DAW1,
                email = "marta.delgado@example.com",
                subjects = arrayListOf(Subject.PSP, Subject.SI, Subject.LM)
            )
        )

        // Mock de la respuesta de Retrofit
        val mockResponse = Response.success(students)

        // Definir el comportamiento del mock
        `when`(studentService.getStudents()).thenReturn(mockResponse)

        // Ejecutar el métdo y verificar la respuesta
        val response = studentService.getStudents()

        // Validar que el número de estudiantes sea correcto
        assertEquals(10, response.body()?.size)
    }

    @Test
    fun getStudentsReturnsFailure() = runBlocking {

        // Crear una respuesta de error simulada
        val errorResponse =
            Response.error<List<Alumno>>(500, ResponseBody.create(null, "Internal Server Error"))

        // Definir el comportamiento del mock para que retorne un error
        `when`(studentService.getStudents()).thenReturn(errorResponse)

        // Ejecutar el método y verificar la respuesta
        val response = studentService.getStudents()

        // Validar que la respuesta no tiene un cuerpo
        assertNull(response.body())
    }

    @Test
    fun getByCourseReturnSuccesWitchStudentsList() = runBlocking {

        val course = "DAM1"
        val studentsDAM1 = listOf(
            Alumno(
                id = 1,
                name = "Carlos",
                birthDate = "2003-05-10",
                curso = Curso.DAM1,
                email = "carlos.martinez@example.com",
                subjects = arrayListOf(Subject.PMDM, Subject.AAD, Subject.PSP)
            ),
            Alumno(
                id = 3,
                name = "Mario López",
                birthDate = "2004-01-15",
                curso = Curso.DAM1,
                email = "mario.lopez@example.com",
                subjects = arrayListOf(Subject.SI, Subject.P, Subject.LM)
            ),
            Alumno(
                id = 7,
                name = "David Hernández",
                birthDate = "2005-09-25",
                curso = Curso.DAM1,
                email = "david.hernandez@example.com",
                subjects = arrayListOf(Subject.SI, Subject.AAD, Subject.DDI)
            ),
            Alumno(
                id = 9,
                name = "Carlos Ortega",
                birthDate = "2003-10-20",
                curso = Curso.DAM1,
                email = "carlos.ortega@example.com",
                subjects = arrayListOf(Subject.AAD, Subject.SOM, Subject.P)
            )
        )

        val mockResponse = Response.success(studentsDAM1)

        `when`(studentService.getByCourse(course)).thenReturn(mockResponse)

        val response = studentService.getByCourse(course)

        assertEquals(4, response.body()?.size)

    }

    @Test
    fun getByCourseReturnFailure() = runBlocking {

        val course = "DAM1"

        val errorResponse =
            Response.error<List<Alumno>>(500, ResponseBody.create(null, "Internal Server Error"))

        `when`(studentService.getByCourse(course)).thenReturn(errorResponse)

        val response = studentService.getByCourse(course)

        assertNull(response.body())
    }

    @Test
    fun getStudentsByNameReturnSuccessWithStudent() = runBlocking {

        val name = "Ariana Grande"

        val student = Alumno(
            id = 17,
            name = "Ariana Grande",
            birthDate = "1993-06-26",
            curso = Curso.DAM2,
            email = "ariana.grande@example.com",
            subjects = arrayListOf(Subject.PMDM, Subject.AAD, Subject.PSP)
        )

        val mockitoResponse = Response.success(student)

        `when`(studentService.getByName(name)).thenReturn(mockitoResponse)

        val response = studentService.getByName(name)

        assertEquals(student, response.body())

    }

    @Test
    fun getStudentsByNameReturnFailure() = runBlocking {

        val name = "Pedro petete"

        val errorResponse =
            Response.error<Alumno>(500, ResponseBody.create(null, "No content"))

        `when`(studentService.getByName(name)).thenReturn(errorResponse)

        val response = studentService.getByName(name)

        assertNull(response.body())

    }

    @Test
    fun getStudentByIdReturnSuccessWithStudent() = runBlocking {

        val id = "17"

        val student = Alumno(
            id = 17,
            name = "Ariana Grande",
            birthDate = "1993-06-26",
            curso = Curso.DAM2,
            email = "ariana.grande@example.com",
            subjects = arrayListOf(Subject.PMDM, Subject.AAD, Subject.PSP)
        )

        val mockResponse = Response.success(student)

        `when`(studentService.getById(id)).thenReturn(mockResponse)

        val response = studentService.getById(id)

        assertEquals(student, response.body())

    }

    @Test
    fun getStudentByIdReturnFailure() = runBlocking {

        val id = "17"

        val errorResponse =
            Response.error<Alumno>(500, ResponseBody.create(null, "Not found id $id"))

        `when`(studentService.getById(id)).thenReturn(errorResponse)

        val response = studentService.getById(id)

        assertNull(response.body())

    }

    @Test
    fun addStudentReturnSuccesWithAdedStudent() = runBlocking {

        val newStudent = Alumno(
            id = 11,
            name = "Elena Martín",
            birthDate = "2005-12-01",
            curso = Curso.DAM1,
            email = "elena.martin@example.com",
            subjects = arrayListOf(Subject.PSP, Subject.DDI, Subject.LM)
        )

        val mockResponse = Response.success(newStudent)

        `when`(studentService.addStudent(newStudent)).thenReturn(mockResponse)

        val response = studentService.addStudent(newStudent)

        assertEquals(newStudent, response.body())

    }

    @Test
    fun addStudentReturnSuccesListSizeIncreased() = runBlocking {

        val students = arrayListOf(
            Alumno(
                id = 1,
                name = "Carlos",
                birthDate = "2003-05-10",
                curso = Curso.DAM1,
                email = "carlos.martinez@example.com",
                subjects = arrayListOf(Subject.PMDM, Subject.AAD, Subject.PSP)
            ),
            Alumno(
                id = 2,
                name = "Lucía Fernández",
                birthDate = "2002-11-22",
                curso = Curso.DAM2,
                email = "lucia.fernandez@example.com",
                subjects = arrayListOf(Subject.DDI, Subject.SOM, Subject.SER)
            ),
            Alumno(
                id = 3,
                name = "Mario López",
                birthDate = "2004-01-15",
                curso = Curso.DAM1,
                email = "mario.lopez@example.com",
                subjects = arrayListOf(Subject.SI, Subject.P, Subject.LM)
            ),
            Alumno(
                id = 4,
                name = "Ana",
                birthDate = "2003-03-30",
                curso = Curso.DAW2,
                email = "ana.garcia@example.com",
                subjects = arrayListOf(Subject.PMDM, Subject.DDI, Subject.SOM)
            ),
            Alumno(
                id = 5,
                name = "Javier Torres",
                birthDate = "2004-07-12",
                curso = Curso.DAW1,
                email = "javier.torres@example.com",
                subjects = arrayListOf(Subject.P, Subject.SOM, Subject.LM)
            ),
            Alumno(
                id = 6,
                name = "Sara Ruiz",
                birthDate = "2003-02-18",
                curso = Curso.DAM2,
                email = "sara.ruiz@example.com",
                subjects = arrayListOf(Subject.DDI, Subject.PMDM, Subject.PSP)
            ),
            Alumno(
                id = 7,
                name = "David Hernández",
                birthDate = "2005-09-25",
                curso = Curso.DAM1,
                email = "david.hernandez@example.com",
                subjects = arrayListOf(Subject.SI, Subject.AAD, Subject.DDI)
            ),
            Alumno(
                id = 8,
                name = "Laura Gómez",
                birthDate = "2004-06-15",
                curso = Curso.DAW2,
                email = "laura.gomez@example.com",
                subjects = arrayListOf(Subject.PMDM, Subject.SER, Subject.P)
            ),
            Alumno(
                id = 9,
                name = "Carlos Ortega",
                birthDate = "2003-10-20",
                curso = Curso.DAM2,
                email = "carlos.ortega@example.com",
                subjects = arrayListOf(Subject.AAD, Subject.SOM, Subject.P)
            ),
            Alumno(
                id = 10,
                name = "Marta Delgado",
                birthDate = "2004-04-05",
                curso = Curso.DAW1,
                email = "marta.delgado@example.com",
                subjects = arrayListOf(Subject.PSP, Subject.SI, Subject.LM)
            )
        )

        val newStudent = Alumno(
            id = 11,
            name = "Elena Martín",
            birthDate = "2005-12-01",
            curso = Curso.DAM1,
            email = "elena.martin@example.com",
            subjects = arrayListOf(Subject.PSP, Subject.DDI, Subject.LM)
        )

        val mockResponse = Response.success(newStudent)

        `when`(studentService.addStudent(newStudent)).thenReturn(mockResponse)

        val response = students.add((studentService.addStudent(newStudent).body()!!))

        val studentsSize = students.size

        assertEquals(11, studentsSize)

    }

    @Test
    fun removeStudentByIdReturnsSuccessWithTrue() = runBlocking {

        val id = "2"

        val mockResponse = Response.success(true)

        `when`(studentService.removeById(id)).thenReturn(mockResponse)

        val response = studentService.removeById(id)

        assertEquals(response, mockResponse)

    }

}
