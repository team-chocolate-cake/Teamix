package com.chocolate.remote

import com.chocolate.repository.datastore.UserEncryptedSharedPreference
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val sharedPreference: UserEncryptedSharedPreference
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestUrl = chain.request().url
        val request = chain.request()
            .newBuilder()
            .header(
                AUTHORIZATION, Credentials.basic(
                    username = sharedPreference.getEmail(),
                    password = sharedPreference.getApiKey()
                )
            )
            .build()
        return chain.proceed(request)
    }

    private companion object {
        const val AUTHORIZATION = "Authorization"
    }
}