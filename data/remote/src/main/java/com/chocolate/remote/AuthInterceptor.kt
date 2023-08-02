package com.chocolate.remote

import com.chocolate.repository.datastore.PreferenceStorage
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val prefs: PreferenceStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestUrl = chain.request().url
        val request = chain.request()
            .newBuilder()
            .url("https://${prefs.currentOrganization}.zulipchat.com/api/v1/")
            .header(
                AUTHORIZATION, Credentials.basic(
                    username = "",
                    password = ""
                )
            )
            .build()
        return chain.proceed(request)
    }

    private companion object {
        const val AUTHORIZATION = "Authorization"
    }
}