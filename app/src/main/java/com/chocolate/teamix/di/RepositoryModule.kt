package com.chocolate.teamix.di

import com.chocolate.repository.repository.ChannelsRepositoryImpl
import com.chocolate.repository.repository.DraftRepositoryImpl
import com.chocolate.repository.repository.MessagesRepositoryImpl
import com.chocolate.repository.repository.OnboardingRepositoryImpl
import com.chocolate.repository.repository.ScheduledMessageRepositoryImpl
import com.chocolate.repository.repository.ServerAndOrganizationsRepositoryImpl
import com.chocolate.repository.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repositories.ChannelsRepository
import repositories.DraftRepository
import repositories.MessagesRepository
import repositories.OnboardingRepository
import repositories.ScheduledMessageRepository
import repositories.ServerAndOrganizationsRepository
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
    abstract fun bindDraftRepository(draftRepository: DraftRepositoryImpl): DraftRepository

    @Binds
    @Singleton
    abstract fun bindScheduledMessageRepository(scheduledMessageRepository: ScheduledMessageRepositoryImpl): ScheduledMessageRepository

    @Binds
    @Singleton
    abstract fun bindServerAndOrganizationsRepository(serverAndOrganizationsRepository: ServerAndOrganizationsRepositoryImpl): ServerAndOrganizationsRepository

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(
        onboardingRepository: OnboardingRepositoryImpl
    ): OnboardingRepository

}