package com.psp.retrofit.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psp.retrofit.R
import com.psp.retrofit.data.StudentDataRepository
import com.psp.retrofit.data.remote.ApiService
import com.psp.retrofit.data.remote.RetrofitClient
import com.psp.retrofit.model.StudentRepository
import kotlinx.coroutines.launch

class StudentFragment : Fragment() {

    private val repository: StudentRepository by lazy {
        StudentDataRepository(RetrofitClient.apiService)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student, container, false) // Asegúrate de usar el ID correcto
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_students)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Asocia el adaptador incluso si los datos aún no están listos
        recyclerView.adapter = StudentAdapter(emptyList()) // Lista vacía temporal
        fetchStudents(recyclerView) // Llama a la función para obtener los datos
    }

    private fun fetchStudents(recyclerView: RecyclerView) {
        lifecycleScope.launch {
            // Obtener los estudiantes desde el repositorio
            val result = repository.getStudents()

            // Verificar si la operación fue exitosa
            if (result.isSuccess) {
                // Si es exitoso, obtener la lista de estudiantes
                val students = result.getOrDefault(emptyList())
                // Configurar el adaptador con la lista de estudiantes
                recyclerView.adapter = StudentAdapter(students)
            } else {
                // Si falla, manejar el error (por ejemplo, mostrar un mensaje)
                val error = result.exceptionOrNull()
                // Mostrar el error o hacer alguna acción con él
            }
        }
    }

}
