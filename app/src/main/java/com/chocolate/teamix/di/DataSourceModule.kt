package com.chocolate.teamix.di

import com.chocolate.local.dao.draft.DraftMessagesLocalDataSourceImpl
import com.chocolate.local.dao.organization.OrganizationsLocalDataSourceImplementation
import com.chocolate.local.dao.stream.StreamLocalDataSourceImpl
import com.chocolate.local.dao.trend.TrendLocalDataSourceImpl
import com.chocolate.local.dao.user.UserLocalDataSourceImpl
import com.chocolate.local.datastore.OrganizationPreferenceDataSourceImplementation
import com.chocolate.remote.channels.implementation.remote.ChannelsImpl
import com.chocolate.remote.drafts.DraftsMessagesImpl
import com.chocolate.remote.messages.MessagesImpl
import com.chocolate.remote.scheduled_message.ScheduledMessageImpl
import com.chocolate.remote.server_and_organizations.OrganizationsImpl
import com.chocolate.remote.users.UsersImpl
import com.chocolate.repository.datastore.OrganizationPreferenceDataSource
import com.chocolate.repository.service.local.DraftMessagesLocalDataSource
import com.chocolate.repository.service.local.OrganizationsLocalDataSource
import com.chocolate.repository.service.local.StreamLocalDataSource
import com.chocolate.repository.service.local.TrendLocalDataSource
import com.chocolate.repository.service.local.UserLocalDataSource
import com.chocolate.repository.service.remote.ChannelsDataSource
import com.chocolate.repository.service.remote.DraftMessageDataSource
import com.chocolate.repository.service.remote.MessagesDataSource
import com.chocolate.repository.service.remote.OrganizationDataSource
import com.chocolate.repository.service.remote.ScheduledMessageDataSource
import com.chocolate.repository.service.remote.UsersDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideOrganizationPreferenceDataSource(
        organizationPreferenceDataSourceImplementation: OrganizationPreferenceDataSourceImplementation
    ): OrganizationPreferenceDataSource

    @Binds
    abstract fun provideDraftMessageDataSource(
        draftMessagesLocalDataSourceImpl: DraftMessagesLocalDataSourceImpl
    ): DraftMessagesLocalDataSource

    @Binds
    abstract fun provideOrganizationsDataSource(
        organizationsLocalDataSourceImplementation: OrganizationsLocalDataSourceImplementation
    ): OrganizationsLocalDataSource

    @Binds
    abstract fun provideStreamDataSource(
        streamLocalDataSourceImpl: StreamLocalDataSourceImpl
    ): StreamLocalDataSource

    @Binds
    abstract fun provideTrendDataSource(
        trendLocalDataSourceImpl: TrendLocalDataSourceImpl
    ): TrendLocalDataSource

    @Binds
    abstract
    fun provideUserDataSource(
        userLocalDataSourceImpl: UserLocalDataSourceImpl
    ): UserLocalDataSource

    @Binds
    abstract fun provideChannels(channelsImpl: ChannelsImpl): ChannelsDataSource

    @Binds
    abstract fun provideOrganization(organizationsImpl: OrganizationsImpl): OrganizationDataSource

    @Binds
    abstract fun provideDraftsMessage(draftsMessagesImpl: DraftsMessagesImpl): DraftMessageDataSource

    @Binds
    abstract fun provideMessages(messagesImpl: MessagesImpl): MessagesDataSource

    @Binds
    abstract fun provideScheduledMessage(scheduledMessageImpl: ScheduledMessageImpl): ScheduledMessageDataSource

    @Binds
    abstract fun provideUsers(usersImpl: UsersImpl): UsersDataSource

}