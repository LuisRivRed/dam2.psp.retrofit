package com.psp.data.remote

import okhttp3.Interceptor
import okhttp3.Response

object AuthInterceptor : Interceptor {
    private var token: String? = null

    fun setToken(newToken: String) {
        token = newToken
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()

        token?.let {
            newRequest.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(newRequest.build())
    }
}