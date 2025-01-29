package com.psp.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private val authInterceptor = AuthInterceptor()

    //Cambiar la ip en el archivo network_security_config.xml en caso de usar un movil fisico
    //private const val BASE_URL_API = "http://192.168.x.x:8080/" ruta si se usa un movil fisico
    private const val BASE_URL_API =
        "http://10.0.2.2:8080/" //ruta si se usa emulador de android studio

    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun setToken(token: String) {
        authInterceptor.setToken(token)
    }
}