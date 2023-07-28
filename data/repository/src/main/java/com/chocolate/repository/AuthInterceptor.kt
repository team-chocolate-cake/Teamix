package com.chocolate.repository

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
                .newBuilder()
                .header(AUTHORIZATION,Credentials.basic(username = "ms.akkad@std.alaqsa.edu.ps", password ="weDiTDQgvpLWld8W88YTnR5pzfCSVKm6" ))
                .build()
        return chain.proceed(request)
    }
    private companion object{
        const val AUTHORIZATION = "Authorization"
        const val BASIC_AUTH = "basic auth"
    }
}