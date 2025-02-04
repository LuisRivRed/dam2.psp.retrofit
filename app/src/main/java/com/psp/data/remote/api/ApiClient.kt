@file:JvmName("ApiServiceKt")

package com.psp.data.remote.api

import com.google.gson.GsonBuilder
import com.psp.model.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

// Cambiado la URL base para usar HTTPS y el puerto SSL
const val BASE_URL = "https://192.168.56.1:8443/"

class ApiClient {

    val gson = GsonBuilder()
        .setLenient()
        .create()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Hacer el interceptor público para que pueda ser accedido desde fuera
    val authInterceptor = AuthInterceptor()

    // Creamos un TrustManager que acepta todos los certificados (solo para desarrollo)
    private val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    })

    // Configuracion del cliente OkHttp con SSL
    private val client = OkHttpClient.Builder().apply {
        // Añadimos los interceptores existentes
        addInterceptor(loggingInterceptor)
        addInterceptor(authInterceptor)

        // Configuramos SSL para desarrollo
        try {
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Asignamos el socket factory
            sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)

            // Configuramos el verificador de hostname para aceptar todos (solo desarrollo)
            hostnameVerifier { _, _ -> true }
        } catch (e: Exception) {
            throw RuntimeException("Failed to configure SSL", e)
        }
    }.build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}