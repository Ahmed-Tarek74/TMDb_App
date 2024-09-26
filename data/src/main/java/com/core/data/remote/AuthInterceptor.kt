package com.core.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor (private val apiToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        // Add the Authorization header to the original request
        val requestWithToken = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $apiToken")
            .build()
        return chain.proceed(requestWithToken)
    }
}