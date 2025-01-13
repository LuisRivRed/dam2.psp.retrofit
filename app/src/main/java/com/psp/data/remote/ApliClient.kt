package com.psp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApliClient {
    //Cambiar la ip en el archivo network_security_config.xml en caso de usar un movil fisico
    //private const val BASE_URL_API = "http://192.168.x.x:8080/" ruta si se usa un movil fisicoprivate
 const val BASE_URL_API = "http://10.0.2.2:8080/" //ruta si se usa emulador de android studio
    fun provideRetrofit(): Retrofit {
     return Retrofit.Builder()
         .baseUrl(BASE_URL_API)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
    }
}