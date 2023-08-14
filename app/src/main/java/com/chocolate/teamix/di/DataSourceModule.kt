package com.chocolate.teamix.di

import com.chocolate.local.dao.draft.DraftMessagesLocalDataSource
import com.chocolate.local.dao.organization.OrganizationsLocalDataSource
import com.chocolate.local.dao.stream.StreamLocalDataSource
import com.chocolate.local.dao.trend.TrendLocalDataSource
import com.chocolate.local.dao.user.UserLocalDataSource
import com.chocolate.local.datastore.OnboardingPreferencesDataSource
import com.chocolate.local.datastore.OrganizationPreferencesDataSource
import com.chocolate.local.datastore.UserPreferencesDataSource
import com.chocolate.remote.RetrofitDataSource
import com.chocolate.repository.datastore.OnboardingDataStoreDataSource
import com.chocolate.repository.datastore.OrganizationDataStoreDataSource
import com.chocolate.repository.datastore.UserDataStoreDataSource
import com.chocolate.repository.service.local.DraftMessagesRoomDataSource
import com.chocolate.repository.service.local.OrganizationsRoomDataSource
import com.chocolate.repository.service.local.StreamRoomDataSource
import com.chocolate.repository.service.local.TrendRoomDataSource
import com.chocolate.repository.service.local.UserRoomDataSource
import com.chocolate.repository.service.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindOrganizationPreferenceDataSource(
        organizationPreferencesDataSource: OrganizationPreferencesDataSource
    ): OrganizationDataStoreDataSource

    @Singleton
    @Binds
    abstract fun bindDraftMessageDataSource(
        draftMessagesLocalDataSource: DraftMessagesLocalDataSource
    ): DraftMessagesRoomDataSource

    @Singleton
    @Binds
    abstract fun bindOrganizationsDataSource(
        organizationsLocalDataSource: OrganizationsLocalDataSource
    ): OrganizationsRoomDataSource

    @Singleton
    @Binds
    abstract fun bindStreamDataSource(
        streamLocalDataSource: StreamLocalDataSource
    ): StreamRoomDataSource

    @Singleton
    @Binds
    abstract fun bindTrendDataSource(
        trendLocalDataSource: TrendLocalDataSource
    ): TrendRoomDataSource

    @Singleton
    @Binds
    abstract
    fun bindUserDataSource(
        userLocalDataSource: UserLocalDataSource
    ): UserRoomDataSource

    @Singleton
    @Binds
    abstract fun bindOnboardingPreferencesDataSource(
        onboardingPreferencesDataSource: OnboardingPreferencesDataSource
    ): OnboardingDataStoreDataSource

    @Singleton
    @Binds
    abstract fun bindUserEncryptedSharedPreference(
        userPreferencesDataSource: UserPreferencesDataSource
    ): UserDataStoreDataSource

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(retrofitDataSource: RetrofitDataSource): RemoteDataSource
}