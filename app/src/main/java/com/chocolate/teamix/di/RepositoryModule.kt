package com.chocolate.teamix.di

import com.chocolate.repository.repository.ChannelsRepositoryImpl
import com.chocolate.repository.repository.MessagesRepositoryImpl
import com.chocolate.repository.repository.OrganizationsRepositoryImpl
import com.chocolate.repository.repository.TopicRepositoryImpl
import com.chocolate.repository.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repositories.ChannelsRepository
import repositories.MessagesRepository
import repositories.OrganizationsRepository
import repositories.TopicRepository
import repositories.UsersRepository
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
    abstract fun bindUserRepository(userRepositories: UserRepositoryImpl): UsersRepository

    @Binds
    @Singleton
    abstract fun bindServerAndOrganizationsRepository(serverAndOrganizationsRepository: OrganizationsRepositoryImpl): OrganizationsRepository

    @Binds
    @Singleton
    abstract fun bindsTopicRepository(topicRepositoryImpl: TopicRepositoryImpl):TopicRepository
}