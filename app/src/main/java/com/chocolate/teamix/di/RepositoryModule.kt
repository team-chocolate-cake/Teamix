package com.chocolate.teamix.di

import com.chocolate.repository.repository.AppSettingsRepositoryImpl
import com.chocolate.repository.repository.ChannelsRepositoryImpl
import com.chocolate.repository.repository.MessagesRepositoryImpl
import com.chocolate.repository.repository.OrganizationsRepositoryImpl
import com.chocolate.repository.repository.MemberRepositoryImpl
import com.chocolate.repository.repository.TopicRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repositories.AppSettingsRepository
import repositories.ChannelsRepository
import repositories.MessagesRepository
import repositories.OrganizationsRepository
import repositories.MemberRepository
import repositories.TopicRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMessagesRepository(messagesRepository: MessagesRepositoryImpl): MessagesRepository

    @Binds
    @Singleton
    abstract fun bindChannelsRepository(channelsRepository: ChannelsRepositoryImpl): ChannelsRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositories: MemberRepositoryImpl): MemberRepository

    @Binds
    @Singleton
    abstract fun bindServerAndOrganizationsRepository(serverAndOrganizationsRepository: OrganizationsRepositoryImpl): OrganizationsRepository

    @Binds
    @Singleton
    abstract fun bindsTopicRepository(topicRepositoryImpl: TopicRepositoryImpl):TopicRepository
    @Binds
    @Singleton
    abstract fun bindAppSettingsRepository(appSettingsRepository: AppSettingsRepositoryImpl): AppSettingsRepository

}