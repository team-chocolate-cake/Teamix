package com.chocolate.remote

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(
                AUTHORIZATION, Credentials.basic(
                    username = "khaled.eid1k1k@gmail.com",
                    password = "z3lDWRSSPv2VCyRrMb7EkSMs9hB7I1ub"
                )
            )
            .build()
        return chain.proceed(request)
    }

    private companion object {
        const val AUTHORIZATION = "Authorization"
    }
}