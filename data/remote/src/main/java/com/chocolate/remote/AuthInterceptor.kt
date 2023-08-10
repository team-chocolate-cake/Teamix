package com.chocolate.remote

import com.chocolate.repository.datastore.OrganizationDataStoreDataSource
import com.chocolate.repository.datastore.UserDataStoreDataSource
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val userSharedPreference: UserDataStoreDataSource,
    private val datastorePreference: OrganizationDataStoreDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestUrl = chain.request().url
        val request = chain.request()
            .newBuilder()
            .header(
                AUTHORIZATION, Credentials.basic(
                    username = userSharedPreference.getEmail(),
                    password = userSharedPreference.getApiKey()
                )
            )
            .build()
        return chain.proceed(request)
    }

    private companion object {
        const val AUTHORIZATION = "Authorization"
    }
}