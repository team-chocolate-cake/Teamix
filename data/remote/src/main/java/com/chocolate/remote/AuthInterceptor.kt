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
                setDomainName(
                    requestUrl.toString(),
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

    private fun setDomainName(url: String, domainName: String): String {
        val startIndex = 8
        val endIndex = url.indexOf(".zulipchat.com")
        return url.substring(0, startIndex) + domainName + url.substring(endIndex)
    }

    private companion object {
        const val AUTHORIZATION = "Authorization"
    }
}