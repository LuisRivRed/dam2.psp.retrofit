package com.psp.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.psp.data.AlunmnoDataRepository
import com.psp.data.remote.ApiClient
import com.psp.data.remote.ApiService
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        }
        main()
    }

    private fun main() {
        val apiService = ApiClient().provideApi().create(ApiService::class.java)
        AlunmnoDataRepository(apiService)

        runBlocking {
            val response1 = apiService.getAlumnos()
            Log.d("@Dev", "${response1.body()}")

            val response2 = apiService.getAlumnosByName("Marcos")
            Log.d("@Dev", "${response2.body()}")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}