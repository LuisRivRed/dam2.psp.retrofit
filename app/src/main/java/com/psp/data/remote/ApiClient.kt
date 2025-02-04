package com.psp.data.remote

import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto singleton que configura y proporciona una instancia de Retrofit para realizar llamadas a la API.
 */
object ApiClient {

    // URL base de la API
    private const val BASE_URL_API = "http://127.0.0.1:8080/"

    /*
        // Interceptor para manejar la autenticación (comentado por ahora)
        private val authInterceptor = AuthInterceptor()

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    */

    // Configuración de Json para manejar claves desconocidas y valores coercitivos
    private val json = Json {
        ignoreUnknownKeys = true // Ignora las claves desconocidas en la respuesta JSON
        coerceInputValues = true // Permite que los valores nulos se conviertan en valores predeterminados
    }

    /**
     * Proporciona una instancia de Retrofit con la configuración básica.
     * @return Una instancia de Retrofit.
     */
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_API) // Define la URL base de la API
            .addConverterFactory(GsonConverterFactory.create()) // Usa Gson para convertir JSON
            .build()
    }

    /**
     * Instancia perezosa de ApiService.
     * Se inicializa solo cuando se accede por primera vez.
     */
    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java) // Crea la implementación de la API
    }
}

/*
    // Método para establecer un token de autenticación (comentado por ahora)
    fun setToken(token: String) {
        authInterceptor.setToken(token)
    }
*/
