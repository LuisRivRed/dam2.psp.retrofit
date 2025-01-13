import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.psp.data.AlumnoApiClient
import com.psp.model.Alumno
import com.psp.model.Asignatura
import com.psp.model.Curso
import com.psp.retrofit.ui.theme.RetrofitTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {}

        retrofitEjemplo()
    }
}

fun retrofitEjemplo() {
    val apiAlumno = AlumnoApiClient().apiService

    runBlocking {
        Log.d("@prueba", "${apiAlumno.getAlumnos()}")

        Log.d("@prueba", "${apiAlumno.getAlumnosByCurso("DAM1")}")

        Log.d("@prueba", "${apiAlumno.getAlumnoByNombre("Kai")}")

        val alumnoDeEjemplo = Alumno(
            5,
            "Juan   ",
            "2001-10-08",
            Curso.DAW1,
            "juangararr@email.com",
            listOf(Asignatura.PSP, Asignatura.DDI, Asignatura.AAD)
        )
        Log.d("@prueba", "${apiAlumno.addAlumno(alumnoDeEjemplo)}")
        Log.d("@prueba", "${apiAlumno.getAlumnos()}")

        apiAlumno.deleteAlumno(5)
        Log.d("@prueba", "${apiAlumno.getAlumnos()}")


    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitTheme {
        Greeting("Android")
    }
}