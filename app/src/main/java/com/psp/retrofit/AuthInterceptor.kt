package com.psp.retrofit

import okhttp3.Interceptor

class AuthInterceptor : Interceptor {
    private var token: String? = null
    fun setToken(newToken: String) {
        token = newToken
    }

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        token?.let {
            val newRequest = request.newBuilder().addHeader("Authorization", "Bearer $it").build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(request)
    }
}