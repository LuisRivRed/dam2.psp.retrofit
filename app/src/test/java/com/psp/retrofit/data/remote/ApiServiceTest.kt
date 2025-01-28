package com.psp.retrofit.data.remote

import com.psp.retrofit.data.StudentDataRepository
import com.psp.retrofit.model.Course
import com.psp.retrofit.model.Student
import com.psp.retrofit.model.StudentRepository
import com.psp.retrofit.model.Subject
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ApiServiceTest {

    @Mock
    private lateinit var apiService: ApiService
    private lateinit var studentRepository: StudentRepository
    private val mockStudents = listOf(
        Student(
            id = 1,
            name = "Bob",
            email = "bob@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.ED, Subject.PRG, Subject.EIE, Subject.BBDD)
        ),
        Student(
            id = 2,
            name = "Eve",
            email = "eve@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.BBDD, Subject.DDI, Subject.FOL)
        ),
        Student(
            id = 3,
            name = "Nina",
            email = "nina@dam2.com",
            course = Course.DAM2,
            subjects = listOf(
                Subject.PRG,
                Subject.PMDM,
                Subject.PSP,
                Subject.DDI,
                Subject.EIE
            )
        ),
        Student(
            id = 4,
            name = "Isaac",
            email = "isaac@daw2.com",
            course = Course.DAW2,
            subjects = listOf(
                Subject.FOL,
                Subject.SI,
                Subject.BBDD,
                Subject.SGE,
                Subject.PSP
            )
        ),
        Student(
            id = 5,
            name = "Hannah",
            email = "hannah@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.SGE, Subject.DDI, Subject.ED, Subject.SI, Subject.FOL)
        ),
        Student(
            id = 6,
            name = "Grace",
            email = "grace@dam2.com",
            course = Course.DAM2,
            subjects = listOf(
                Subject.PRG,
                Subject.BBDD,
                Subject.SGE,
                Subject.SI,
                Subject.ED
            )
        ),
        Student(
            id = 7,
            name = "Rachel",
            email = "rachel@dam2.com",
            course = Course.DAM2,
            subjects = listOf(
                Subject.LMSGI,
                Subject.ADD,
                Subject.SI,
                Subject.PRG,
                Subject.DDI
            )
        ),
        Student(
            id = 8,
            name = "Quinn",
            email = "quinn@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.DDI, Subject.BBDD, Subject.FOL)
        ),
        Student(
            id = 9,
            name = "Mike",
            email = "mike@dam1.com",
            course = Course.DAM1,
            subjects = listOf(
                Subject.PRG,
                Subject.EIE,
                Subject.BBDD,
                Subject.ADD,
                Subject.PSP
            )
        ),
        Student(
            id = 10,
            name = "Oscar",
            email = "oscar@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.DDI, Subject.FOL, Subject.ED)
        ),
        Student(
            id = 11,
            name = "Frank",
            email = "frank@daw2.com",
            course = Course.DAW2,
            subjects = listOf(
                Subject.BBDD,
                Subject.SGE,
                Subject.PSP,
                Subject.PRG,
                Subject.LMSGI
            )
        ),
        Student(
            id = 12,
            name = "Steve",
            email = "steve@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.SGE)
        ),
        Student(
            id = 13,
            name = "Kevin",
            email = "kevin@dam1.com",
            course = Course.DAM1,
            subjects = listOf(
                Subject.EIE,
                Subject.PMDM,
                Subject.LMSGI,
                Subject.PSP,
                Subject.SI
            )
        ),
        Student(
            id = 14,
            name = "Alice",
            email = "alice@daw2.com",
            course = Course.DAW2,
            subjects = listOf(
                Subject.PRG,
                Subject.BBDD,
                Subject.DDI,
                Subject.SI,
                Subject.FOL
            )
        ),
        Student(
            id = 15,
            name = "Diana",
            email = "diana@dam2.com",
            course = Course.DAM2,
            subjects = listOf(
                Subject.FOL,
                Subject.PMDM,
                Subject.ED,
                Subject.ADD,
                Subject.LMSGI
            )
        ),
        Student(
            id = 16,
            name = "Charlie",
            email = "charlie@dam2.com",
            course = Course.DAM2,
            subjects = listOf(
                Subject.SI,
                Subject.SGE,
                Subject.PSP,
                Subject.FOL,
                Subject.LMSGI
            )
        ),
        Student(
            id = 17,
            name = "Julia",
            email = "julia@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.SGE)
        ),
        Student(
            id = 18,
            name = "Lily",
            email = "lily@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.SI, Subject.LMSGI, Subject.PSP)
        ),
        Student(
            id = 19,
            name = "Paul",
            email = "paul@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.LMSGI, Subject.EIE, Subject.DDI, Subject.FOL)
        ),
        Student(
            id = 20,
            name = "Tina",
            email = "tina@dam2.com",
            course = Course.DAM2,
            subjects = listOf(
                Subject.DDI,
                Subject.FOL,
                Subject.ED,
                Subject.PSP,
                Subject.PMDM
            )
        ),
        Student(
            id = 21,
            name = "Mark",
            email = "mark@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.FOL, Subject.SI, Subject.BBDD)
        ),
        Student(
            id = 22,
            name = "Anna",
            email = "anna@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.ED, Subject.PMDM, Subject.LMSGI, Subject.BBDD)
        ),
        Student(
            id = 23,
            name = "Henry",
            email = "henry@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.PRG, Subject.DDI, Subject.FOL)
        ),
        Student(
            id = 24,
            name = "Sarah",
            email = "sarah@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.BBDD, Subject.SGE, Subject.FOL)
        ),
        Student(
            id = 25,
            name = "Nathan",
            email = "nathan@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.ADD, Subject.PSP, Subject.DDI)
        ),
        Student(
            id = 26,
            name = "Olivia",
            email = "olivia@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.PRG, Subject.LMSGI, Subject.BBDD, Subject.FOL)
        ),
        Student(
            id = 27,
            name = "Mia",
            email = "mia@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.SI, Subject.DDI, Subject.PMDM)
        ),
        Student(
            id = 28,
            name = "Noah",
            email = "noah@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.EIE, Subject.FOL, Subject.SGE)
        ),
        Student(
            id = 29,
            name = "Chloe",
            email = "chloe@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.ADD, Subject.SI, Subject.PRG)
        ),
        Student(
            id = 30,
            name = "Sophia",
            email = "sophia@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.LMSGI, Subject.DDI, Subject.BBDD)
        ),
        Student(
            id = 31,
            name = "Logan",
            email = "logan@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.PSP, Subject.SGE, Subject.FOL)
        ),
        Student(
            id = 32,
            name = "Emma",
            email = "emma@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.SI, Subject.BBDD, Subject.DDI)
        ),
        Student(
            id = 33,
            name = "Liam",
            email = "liam@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.PRG, Subject.LMSGI, Subject.PMDM)
        ),
        Student(
            id = 34,
            name = "Emily",
            email = "emily@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.SGE, Subject.ADD, Subject.PMDM)
        ),
        Student(
            id = 35,
            name = "James",
            email = "james@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.EIE, Subject.FOL, Subject.SGE)
        ),
        Student(
            id = 36,
            name = "Ella",
            email = "ella@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.PRG, Subject.PSP, Subject.DDI)
        ),
        Student(
            id = 37,
            name = "Aiden",
            email = "aiden@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.BBDD, Subject.SI, Subject.FOL)
        ),
        Student(
            id = 38,
            name = "Ava",
            email = "ava@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.PMDM, Subject.ADD, Subject.PRG)
        ),
        Student(
            id = 39,
            name = "Ethan",
            email = "ethan@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.FOL, Subject.LMSGI, Subject.PMDM)
        ),
        Student(
            id = 40,
            name = "Grace",
            email = "grace@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.SI, Subject.PSP, Subject.DDI)
        ),
        Student(
            id = 41,
            name = "Lucas",
            email = "lucas@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.EIE, Subject.SGE, Subject.FOL)
        ),
        Student(
            id = 42,
            name = "Isabella",
            email = "isabella@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.LMSGI, Subject.BBDD, Subject.PRG)
        ),
        Student(
            id = 43,
            name = "Mason",
            email = "mason@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.ADD, Subject.SGE, Subject.DDI)
        ),
        Student(
            id = 44,
            name = "Lily",
            email = "lily@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.SI, Subject.PMDM, Subject.FOL)
        ),
        Student(
            id = 45,
            name = "Oliver",
            email = "oliver@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.EIE, Subject.PRG, Subject.BBDD)
        ),
        Student(
            id = 46,
            name = "Zoe",
            email = "zoe@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.LMSGI, Subject.FOL, Subject.PSP)
        ),
        Student(
            id = 47,
            name = "Benjamin",
            email = "benjamin@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.ADD, Subject.SI, Subject.PRG)
        ),
        Student(
            id = 48,
            name = "Amelia",
            email = "amelia@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.BBDD, Subject.LMSGI, Subject.PMDM)
        ),
        Student(
            id = 49,
            name = "Elijah",
            email = "elijah@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.SI, Subject.PSP, Subject.ADD)
        ),
        Student(
            id = 50,
            name = "Abigail",
            email = "abigail@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.FOL, Subject.BBDD, Subject.PRG)
        ),
        Student(
            id = 51,
            name = "Daniel",
            email = "daniel@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.PSP, Subject.PMDM, Subject.EIE)
        ),
        Student(
            id = 52,
            name = "Charlotte",
            email = "charlotte@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.DDI, Subject.LMSGI, Subject.BBDD)
        ),
        Student(
            id = 53,
            name = "Matthew",
            email = "matthew@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.SI, Subject.ADD, Subject.PSP)
        ),
        Student(
            id = 54,
            name = "Harper",
            email = "harper@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.PRG, Subject.BBDD, Subject.FOL)
        ),
        Student(
            id = 55,
            name = "Jackson",
            email = "jackson@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.PMDM, Subject.ADD, Subject.SI)
        ),
        Student(
            id = 56,
            name = "Hannah",
            email = "hannah@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.EIE, Subject.PRG, Subject.LMSGI)
        ),
        Student(
            id = 57,
            name = "Sebastian",
            email = "sebastian@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.PSP, Subject.ADD, Subject.BBDD)
        ),
        Student(
            id = 58,
            name = "Aria",
            email = "aria@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.SGE, Subject.FOL, Subject.PRG)
        ),
        Student(
            id = 59,
            name = "Carter",
            email = "carter@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.LMSGI, Subject.DDI, Subject.SI)
        ),
        Student(
            id = 60,
            name = "Avery",
            email = "avery@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.EIE, Subject.PMDM, Subject.BBDD)
        ),
        Student(
            id = 61,
            name = "Wyatt",
            email = "wyatt@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.PRG, Subject.ADD, Subject.FOL)
        ),
        Student(
            id = 62,
            name = "Ella",
            email = "ella@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.LMSGI, Subject.DDI, Subject.PSP)
        ),
        Student(
            id = 63,
            name = "Gabriel",
            email = "gabriel@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.BBDD, Subject.SI, Subject.ADD)
        ),
        Student(
            id = 64,
            name = "Scarlett",
            email = "scarlett@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.EIE, Subject.LMSGI, Subject.FOL)
        ),
        Student(
            id = 65,
            name = "Jayden",
            email = "jayden@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.PRG, Subject.SGE, Subject.PMDM)
        ),
        Student(
            id = 66,
            name = "Layla",
            email = "layla@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.DDI, Subject.BBDD, Subject.SI)
        ),
        Student(
            id = 67,
            name = "Levi",
            email = "levi@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.ADD, Subject.EIE, Subject.FOL)
        ),
        Student(
            id = 68,
            name = "Sofia",
            email = "sofia@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.PSP, Subject.LMSGI, Subject.BBDD)
        ),
        Student(
            id = 69,
            name = "Caleb",
            email = "caleb@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.SI, Subject.SGE, Subject.ADD)
        ),
        Student(
            id = 70,
            name = "Victoria",
            email = "victoria@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.PMDM, Subject.EIE, Subject.FOL)
        ),
        Student(
            id = 71,
            name = "Ryan",
            email = "ryan@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.BBDD, Subject.PRG, Subject.SGE)
        ),
        Student(
            id = 72,
            name = "Luna",
            email = "luna@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.LMSGI, Subject.PRG, Subject.PSP)
        ),
        Student(
            id = 73,
            name = "Leo",
            email = "leo@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.ADD, Subject.DDI, Subject.BBDD)
        ),
        Student(
            id = 74,
            name = "Isla",
            email = "isla@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.EIE, Subject.SI, Subject.PRG)
        ),
        Student(
            id = 75,
            name = "Nathan",
            email = "nathan@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.SGE, Subject.PMDM, Subject.FOL)
        ),
        Student(
            id = 76,
            name = "Hazel",
            email = "hazel@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.ADD, Subject.LMSGI, Subject.PSP)
        ),
        Student(
            id = 77,
            name = "Owen",
            email = "owen@daw2.com",
            course = Course.DAW2,
            subjects = listOf(Subject.PRG, Subject.BBDD, Subject.DDI)
        ),
        Student(
            id = 78,
            name = "Lillian",
            email = "lillian@dam1.com",
            course = Course.DAM1,
            subjects = listOf(Subject.SI, Subject.ADD, Subject.FOL)
        ),
        Student(
            id = 79,
            name = "Julian",
            email = "julian@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.SGE, Subject.LMSGI, Subject.EIE)
        ),
        Student(
            id = 80,
            name = "Zoe",
            email = "zoe@daw1.com",
            course = Course.DAW1,
            subjects = listOf(Subject.PMDM, Subject.BBDD, Subject.LMSGI)
        ),
        Student(
            id = 81,
            name = "Aaron",
            email = "aaron@dam2.com",
            course = Course.DAM2,
            subjects = listOf(Subject.PRG, Subject.ADD, Subject.PMDM)
        )
    )
    private val mockStudent = Student(id = 2, name = "Eve", email = "eve@dam1.com", course = Course.DAM1, subjects = listOf(Subject.BBDD, Subject.DDI, Subject.FOL))

    @Before
    fun setUp() {
        studentRepository = StudentDataRepository(apiService)
    }

    @Test
    fun getStudents() =
        runTest {
            whenever(apiService.requestStudents()).thenReturn(
                Response.success(mockStudents)
            )
            val resultado = studentRepository.getStudents()
            Assert.assertTrue(resultado.isSuccessful)
            Assert.assertEquals(mockStudents, resultado.body())
        }

    @Test
    fun getStudentsNull() =
        runTest {
            val mockStudentNull = null
            whenever(apiService.requestStudents()).thenReturn(
                Response.success(mockStudentNull)
            )
            val resultado = studentRepository.getStudents()
            Assert.assertTrue(resultado.isSuccessful)
            Assert.assertNull(resultado.body())
        }

    @Test
    fun getEmptyStudents() =
        runTest {
            val mockEmptyStudents = emptyList<Student>()
            whenever(apiService.requestStudents()).thenReturn(
                Response.success(mockEmptyStudents)
            )
            val resultado = studentRepository.getStudents()
            Assert.assertTrue(resultado.isSuccessful)
            Assert.assertEquals(mockEmptyStudents, resultado.body())
        }

    @Test
    fun getStudentById() =
        runTest {
            whenever(apiService.requestStudentById(2)).thenReturn(
                Response.success(mockStudent)
            )
            val resultado = studentRepository.getStudentById(2)
            Assert.assertTrue(resultado.isSuccessful)
            Assert.assertEquals(mockStudent, resultado.body())
        }

    @Test
    fun getStudentByName() =
        runTest {
            whenever(apiService.requestStudentByName("Eve")).thenReturn(
                Response.success(listOf(mockStudent))
            )
            val resultado = studentRepository.getStudentByName("Eve")
            Assert.assertTrue(resultado.isSuccessful)
            Assert.assertEquals(listOf(mockStudent), resultado.body())
        }

    @Test
    fun getStudentByEmail() =
        runTest {
            whenever(apiService.requestStudentByEmail("eve@dam1.com")).thenReturn(
                Response.success(mockStudent)
            )
            val resultado = studentRepository.getStudentByEmail("eve@dam1.com")
            Assert.assertTrue(resultado.isSuccessful)
            Assert.assertEquals(mockStudent, resultado.body())
        }

    @Test
    fun getStudentsByCourse() =
        runTest {
            whenever(apiService.requestStudentsByCourse(Course.DAM1)).thenReturn(
                Response.success(mockStudents)
            )
            val resultado = studentRepository.getStudentsByCourse(Course.DAM1)
            Assert.assertTrue(resultado.isSuccessful)
            Assert.assertEquals(mockStudents, resultado.body())
        }

    @Test
    fun getStudentsBySubject() =
        runTest {
            whenever(apiService.requestStudentsBySubject(Subject.BBDD)).thenReturn(
                Response.success(mockStudents)
            )
            val resultado = studentRepository.getStudentsBySubject(Subject.BBDD)
            Assert.assertTrue(resultado.isSuccessful)
            Assert.assertEquals(mockStudents, resultado.body())
        }

    @Test
    fun addStudent() =
        runTest {
            whenever(apiService.addStudent(mockStudent)).thenReturn(
                Response.success(mockStudent)
            )
            val resultado = studentRepository.addStudent(mockStudent)
            Assert.assertTrue(resultado.isSuccessful)
            Assert.assertEquals(mockStudent, resultado.body())
        }

    @Test
    fun deleteStudent() =
        runTest {
            val mockDeleteStudent = true
            whenever(apiService.deleteStudent(2)).thenReturn(
                Response.success(mockDeleteStudent)
            )
            val resultado = studentRepository.deleteStudent(2)
            Assert.assertTrue(resultado.isSuccessful)
            Assert.assertEquals(mockDeleteStudent, resultado.body())
        }
}