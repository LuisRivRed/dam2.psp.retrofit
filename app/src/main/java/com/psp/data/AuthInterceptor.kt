package com.psp.data

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    private var token: String? = null

    fun setToken(newToken: String) {
        token = newToken
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = AlumnoDataRepository.authToken
        val requestBuilder = chain.request().newBuilder()

        if (token.isNullOrEmpty()) {
            println(" No se está enviando un token. Es posible que no se haya guardado correctamente.")
        } else {
            println(" Enviando token: Bearer $token")
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }

}