package com.psp.kotlin

import okhttp3.Interceptor

class AuthInterceptor : Interceptor {
    private var token: String? = null
    fun setToken(newToken: String) {
        token = "Bearer $newToken"
    }

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        return token?.let {
            chain.proceed(
                request.newBuilder()
                    .header("Authorization", it)
                    .build()
            )
        } ?: chain.proceed(request)
    }
}

