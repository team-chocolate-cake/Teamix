package com.chocolate.teamix.di

import com.chocolate.local.datastore.DataStoreDataSource
import com.chocolate.remote.datasource.DirectMessageFirebaseDataSource
import com.chocolate.remote.datasource.ChannelFirebaseDataSource
import com.chocolate.remote.datasource.MemberFirebaseDataSource
import com.chocolate.remote.datasource.TopicsMessageFireBaseDataSourceTopic
import com.chocolate.remote.datasource.OrganizationFirebaseDataSource
import com.chocolate.remote.datasource.SavedLaterFirebaseDataSource
import com.chocolate.remote.datasource.TopicFirebaseDataSource
import com.chocolate.repository.datasource.local.PreferencesDataSource
import com.chocolate.repository.datasource.remote.TopicDataSource
import com.chocolate.repository.datasource.remote.ChannelDataSource
import com.chocolate.repository.datasource.remote.DirectMessageDataSource
import com.chocolate.repository.datasource.remote.MemberDataSource
import com.chocolate.repository.datasource.remote.TopicMessagesDataSource
import com.chocolate.repository.datasource.remote.OrganizationDataSource
import com.chocolate.repository.datasource.remote.SavedLaterDataSource
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
    abstract fun bindPreferencesDataSource(dataStoreDataSource: DataStoreDataSource): PreferencesDataSource

    @Singleton
    @Binds
    abstract fun bindChannelDataSource(channelDataSource: ChannelFirebaseDataSource): ChannelDataSource

    @Singleton
    @Binds
    abstract fun bindMessagesDataSource(messagesDataSource: TopicsMessageFireBaseDataSourceTopic): TopicMessagesDataSource

    @Singleton
    @Binds
    abstract fun bindOrganizationDataSource(organizationFirebaseDataSource: OrganizationFirebaseDataSource): OrganizationDataSource

    @Singleton
    @Binds
    abstract fun bindMemberDataSource(memberFirebaseDataSource: MemberFirebaseDataSource): MemberDataSource

    @Singleton
    @Binds
    abstract fun bindsSavedLaterDataSource(savedLaterFirebaseDataSource: SavedLaterFirebaseDataSource): SavedLaterDataSource

    @Singleton
    @Binds
    abstract fun bindsTopicDataSource(topicFirebaseDataSource: TopicFirebaseDataSource): TopicDataSource

    @Singleton
    @Binds
    abstract fun bindDirectMessageDataSource(directMessageRemoteDataSourceImpl: DirectMessageFirebaseDataSource): DirectMessageDataSource
}