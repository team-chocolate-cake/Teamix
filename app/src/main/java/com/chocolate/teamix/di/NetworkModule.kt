package com.chocolate.teamix.di

import com.chocolate.remote.AuthInterceptor
import com.chocolate.remote.api.DraftService
import com.chocolate.remote.api.MessageService
import com.chocolate.remote.api.OrganizationService
import com.chocolate.repository.datastore.local.PreferencesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "baseUrl"

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        client: OkHttpClient,
        factory: GsonConverterFactory,
        @Named(BASE_URL) baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(factory)
            .build()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideDraftService(retrofit: Retrofit): DraftService =
        retrofit.create(DraftService::class.java)



    @Singleton
    @Provides
    fun provideMessageService(retrofit: Retrofit): MessageService =
        retrofit.create(MessageService::class.java)

    @Singleton
    @Provides
    fun provideOrganizationService(retrofit: Retrofit): OrganizationService =
        retrofit.create(OrganizationService::class.java)

    @Singleton
    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl(preferencesDataSource: PreferencesDataSource): String =
        "https://.zulipchat.com/api/v1/"
}