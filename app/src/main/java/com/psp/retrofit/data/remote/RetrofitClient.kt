package com.psp.retrofit.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://localhost:8080/"  // Cambiar a tu URL real

    private val authInterceptor = AuthInterceptor()

    // Creación de un cliente OkHttp con el interceptor
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)  // Interceptor para añadir el token de autorización
        .build()

    // Configuración de Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)  // Usando el OkHttpClient con el interceptor
        .addConverterFactory(GsonConverterFactory.create())  // Usando Gson para convertir la respuesta JSON a objetos Kotlin
        .build()

    // Creación de la instancia de ApiService
    val apiService: ApiService = retrofit.create(ApiService::class.java)

    // Función para establecer el token de autenticación
    fun setToken(token: String) {
        authInterceptor.setToken(token)
    }
}
