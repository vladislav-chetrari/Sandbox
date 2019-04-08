package io.sandbox.unsplash.data

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
        chain.request().newBuilder()
            .addHeader(API_HEADER_KEY, API_KEY)
            .build()
    )

    private companion object {
        const val API_HEADER_KEY = "x-api-key"
        const val API_KEY = "829a47df-0417-49ae-8d28-92003800352e"
    }
}