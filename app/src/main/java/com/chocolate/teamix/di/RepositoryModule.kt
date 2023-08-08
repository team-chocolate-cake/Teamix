package com.chocolate.teamix.di

import com.chocolate.repository.implementation.channels.ChannelsRepositoryImpl
import com.chocolate.repository.implementation.draft.DraftRepositoryImpl
import com.chocolate.repository.implementation.messages.MessagesRepositoryImpl
import com.chocolate.repository.implementation.onboarding.OnboardingRepositoryImplementation
import com.chocolate.repository.implementation.secheduled_message.ScheduledMessageRepositoryImpl
import com.chocolate.repository.implementation.server_and_organizations.ServerAndOrganizationsRepositoryImpl
import com.chocolate.repository.implementation.users.UserRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import repositories.channels.ChannelsRepository
import repositories.draft.DraftRepository
import repositories.messages.MessagesRepository
import repositories.onboarding.OnboardingRepository
import repositories.scheduled_message.ScheduledMessageRepository
import repositories.server_and_organizations.ServerAndOrganizationsRepository
import repositories.users.UsersRepositories

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMessagesRepository(messagesRepositoryImpl: MessagesRepositoryImpl): MessagesRepository

    @Binds
    @ViewModelScoped
    abstract fun bindChannelsRepository(channelsRepositoryImpl: ChannelsRepositoryImpl): ChannelsRepository

    @Binds
    @ViewModelScoped
    abstract fun bindUserRepository(userRepositoriesImp: UserRepositoryImp): UsersRepositories

    @Binds
    @ViewModelScoped
    abstract fun bindDraftRepository(draftRepositoryImpl: DraftRepositoryImpl): DraftRepository

    @Binds
    @ViewModelScoped
    abstract fun bindScheduledMessageRepository(scheduledMessageRepositoryImpl: ScheduledMessageRepositoryImpl): ScheduledMessageRepository

    @Binds
    @ViewModelScoped
    abstract fun bindServerAndOrganizationsRepository(serverAndOrganizationsRepositoryImpl: ServerAndOrganizationsRepositoryImpl): ServerAndOrganizationsRepository

    @Binds
    @ViewModelScoped
    abstract fun bindOnboardingRepository(
        onboardingRepository: OnboardingRepositoryImplementation
    ): OnboardingRepository

}