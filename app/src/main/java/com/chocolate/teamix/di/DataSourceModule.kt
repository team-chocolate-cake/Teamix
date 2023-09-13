package com.chocolate.teamix.di

import com.chocolate.local.dao.RoomDataSource
import com.chocolate.local.datastore.DataStoreDataSource
import com.chocolate.remote.data_source.AuthenticationDataSourceImpl
import com.chocolate.remote.data_source.ChannelDataSourceImpl
import com.chocolate.remote.data_source.ChannelRetrofitDataSource
import com.chocolate.remote.data_source.DirectMessageRemoteDataSourceImpl
import com.chocolate.remote.data_source.ChannelFireBaseDataSource
import com.chocolate.remote.data_source.MemberRemoteDataSourceImpl
import com.chocolate.remote.data_source.MessagesFireBaseDataSource
import com.chocolate.remote.data_source.OrganizationRemoteRemoteDataSourceImpl
import com.chocolate.remote.data_source.TopicFireBaseDataSource
import com.chocolate.repository.datastore.local.LocalDataSource
import com.chocolate.repository.datastore.local.PreferencesDataSource
import com.chocolate.repository.datastore.realtime.TopicDataSource
import com.chocolate.repository.datastore.remote.ChannelRemoteDataSource
import com.chocolate.repository.datastore.remote.DirectMessageRemoteDataSource
import com.chocolate.repository.datastore.remote.MemberRemoteDataSource
import com.chocolate.repository.datastore.remote.MessagesRemoteDataSource
import com.chocolate.repository.datastore.remote.OrganizationRemoteDataSource
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
    abstract fun bindLocalDataSource(
        roomDataSource: RoomDataSource
    ): LocalDataSource

    @Singleton
    @Binds
    abstract fun bindPreferencesDataSource(
        dataStoreDataSource: DataStoreDataSource
    ): PreferencesDataSource

    @Singleton
    @Binds
    abstract fun bindChannelDataSource(channelDataSource: ChannelFireBaseDataSource):
            ChannelRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindMessagesDataSource(messagesDataSource: MessagesFireBaseDataSource):
            MessagesRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindOrganizationDataSource(organizationRemoteDataSourceImpl: OrganizationRemoteRemoteDataSourceImpl): OrganizationRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindMemberRemoteDataSource(memberRemoteDataSourceImpl: MemberRemoteDataSourceImpl): MemberRemoteDataSource


    @Singleton
    @Binds
    abstract fun bindsTopicDataSource(topicDataSource: TopicFireBaseDataSource): TopicDataSource
    abstract fun bindChannelDataSourceImpl(channelDataSourceImpl: ChannelDataSourceImpl): ChannelDataSource
    @Singleton
    @Binds
    abstract fun bindDMDataSourceImpl(directMessageRemoteDataSource: DirectMessageRemoteDataSourceImpl): DirectMessageRemoteDataSource

}