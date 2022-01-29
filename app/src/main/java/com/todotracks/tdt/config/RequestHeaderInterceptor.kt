package com.todotracks.tdt.config

import okhttp3.Interceptor
import okhttp3.Response

class RequestHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("X-MEMBER-NO", "1")
            .build()
        return chain.proceed(newRequest)
    }
}