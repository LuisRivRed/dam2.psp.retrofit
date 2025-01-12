import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.psp.data.ApiClient
import com.psp.model.Alumno

class ViewModel : ViewModel() {

    fun obtenerAlumnos() {
        viewModelScope.launch {
            try {
                val alumnos: List<Alumno> = ApiClient.retrofit.getAlumnos()
                alumnos.forEach {
                    Log.d("AlumnoViewModel", "Alumno: ${it.nombre}")
                }
            } catch (e: Exception) {
                Log.e("AlumnoViewModel", "Error al obtener los alumnos", e)
            }
        }
    }
}
