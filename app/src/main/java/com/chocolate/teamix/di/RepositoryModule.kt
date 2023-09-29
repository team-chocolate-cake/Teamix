package com.chocolate.teamix.di

import com.chocolate.repository.repository.AppSettingsRepositoryImpl
import com.chocolate.repository.repository.ChannelRepositoryImpl
import com.chocolate.repository.repository.DirectMessageRepositoryImpl
import com.chocolate.repository.repository.TopicsMessageRepositoryImpl
import com.chocolate.repository.repository.OrganizationRepositoryImpl
import com.chocolate.repository.repository.MemberRepositoryImpl
import com.chocolate.repository.repository.SavedLaterRepositoryImpl
import com.chocolate.repository.repository.TopicRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repositories.AppSettingsRepository
import repositories.ChannelRepository
import repositories.DirectMessageRepository
import repositories.TopicsMessageRepository
import repositories.OrganizationRepository
import repositories.MemberRepository
import repositories.SavedLaterRepository
import repositories.TopicRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindTopicsMessageRepository(topicsMessageRepositoryImpl: TopicsMessageRepositoryImpl): TopicsMessageRepository

    @Binds
    @Singleton
    abstract fun bindChannelRepository(channelsRepository: ChannelRepositoryImpl): ChannelRepository

    @Binds
    @Singleton
    abstract fun bindMemberRepository(memberRepositories: MemberRepositoryImpl): MemberRepository

    @Binds
    @Singleton
    abstract fun bindOrganizationRepository(organizationRepository: OrganizationRepositoryImpl): OrganizationRepository

    @Binds
    @Singleton
    abstract fun bindsTopicRepository(topicRepositoryImpl: TopicRepositoryImpl):TopicRepository

    @Binds
    @Singleton
    abstract fun bindAppSettingsRepository(appSettingsRepository: AppSettingsRepositoryImpl): AppSettingsRepository

    @Binds
    @Singleton
    abstract fun bindDirectMessageRepository(directMessageRepositoryImpl: DirectMessageRepositoryImpl): DirectMessageRepository

    @Binds
    @Singleton
    abstract fun bindSavedLaterRepository(savedLaterRepositoryImpl: SavedLaterRepositoryImpl): SavedLaterRepository
}