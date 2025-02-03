package com.psp.retrofit.service

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    private var token: String? = null

    fun setToken(token: String) {
        this.token = "Bearer $token"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", token ?: "")
            .build()
        return chain.proceed(request)
    }
}