package com.chocolate.remote

import com.chocolate.repository.datastore.DataStoreDataSource
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestUrl = chain.request().url
        val request = chain.request()
            .newBuilder()
            .url(
                requestUrl.toString()
                    .replace("null", dataStoreDataSource.currentOrganization ?: "")
            )
            .header(
                AUTHORIZATION, Credentials.basic(
                    username = dataStoreDataSource.getEmail(),
                    password = dataStoreDataSource.getApiKey()
                )
            )
            .build()
        return chain.proceed(request)
    }

    private companion object {
        const val AUTHORIZATION = "Authorization"
    }
}