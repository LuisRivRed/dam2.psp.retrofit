package com.psp.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    private var token: String? = null
    private var logger: ((String) -> Unit)? = null

    fun setToken(newToken: String) {
        token = newToken
    }

    fun setLogger(logger: (String) -> Unit) {
        this.logger = logger
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        token?.let {
            Log.d("@dev", "Añadiendo token a la solicitud: Bearer $it")
            val newRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer $it")
                .build()
            return chain.proceed(newRequest)
        }
        Log.d("@dev", "No se ha añadido token en la solicitud")
        return chain.proceed(request)
    }
}