package com.psp.model

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    private var token: String? = null

    fun setToken(newToken: String) {
        token = newToken
    }

    override fun intercept(chain: Interceptor.Chain): Response{
        var requestBuilder = chain.request().newBuilder()

        if (token.isNullOrEmpty()){
            Log.d("@dev", "Token is null or empty")
        } else {
            Log.d("@dev", "Token is not null or empty")
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(requestBuilder.build())
    }
}