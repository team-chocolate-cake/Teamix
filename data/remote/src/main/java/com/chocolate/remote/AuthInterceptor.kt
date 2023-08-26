package com.chocolate.remote

import com.chocolate.repository.datastore.local.PreferencesDataSource
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestUrl = chain.request().url
        val request = chain.request()
            .newBuilder()
            .url(
                requestUrl.toString()
                    .replace(
                        "null",
                        preferencesDataSource.currentOrganization() ?: throw IOException()
                    )
            )
            .header(
                AUTHORIZATION, Credentials.basic(
                    username = preferencesDataSource.getEmail(),
                    password = preferencesDataSource.getApiKey()
                )
            )
            .build()
        return chain.proceed(request)
    }

    private companion object {
        const val AUTHORIZATION = "Authorization"
    }
}