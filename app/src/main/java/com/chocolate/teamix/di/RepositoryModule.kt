package com.chocolate.teamix.di

import com.chocolate.repository.repository.ChannelsRepositoryImplementation
import com.chocolate.repository.repository.DraftRepositoryImpl
import com.chocolate.repository.repository.MessagesRepositoryImpl
import com.chocolate.repository.repository.OnboardingRepositoryImpl
import com.chocolate.repository.repository.ScheduledMessageRepositoryImpl
import com.chocolate.repository.repository.ServerAndOrganizationsRepositoryImpl
import com.chocolate.repository.repository.UserRepositoryImp
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
import repositories.UsersRepositories
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMessagesRepository(messagesRepositoryImpl: MessagesRepositoryImpl): MessagesRepository

    @Binds
    @Singleton
    abstract fun bindChannelsRepository(channelsRepositoryImplementation: ChannelsRepositoryImplementation): ChannelsRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoriesImp: UserRepositoryImp): UsersRepositories

    @Binds
    @Singleton
    abstract fun bindDraftRepository(draftRepositoryImpl: DraftRepositoryImpl): DraftRepository

    @Binds
    @Singleton
    abstract fun bindScheduledMessageRepository(scheduledMessageRepositoryImpl: ScheduledMessageRepositoryImpl): ScheduledMessageRepository

    @Binds
    @Singleton
    abstract fun bindServerAndOrganizationsRepository(serverAndOrganizationsRepositoryImpl: ServerAndOrganizationsRepositoryImpl): ServerAndOrganizationsRepository

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(
        onboardingRepository: OnboardingRepositoryImpl
    ): OnboardingRepository

}