package com.chocolate.teamix.di

import com.chocolate.repository.implementation.channels.ChannelsRepositoryImpl
import com.chocolate.repository.implementation.draft.DraftRepositoryImpl
import com.chocolate.repository.implementation.messages.MessagesRepositoryImpl
import com.chocolate.repository.implementation.secheduled_message.ScheduledMessageRepositoryImpl
import com.chocolate.repository.implementation.server_and_organizations.ServerAndOrganizationsRepositoryImpl
import com.chocolate.repository.implementation.users.UserRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import repositories.channels.ChannelsRepository
import repositories.draft.DraftRepository
import repositories.messages.MessagesRepository
import repositories.scheduled_message.ScheduledMessageRepository
import repositories.server_and_organizations.ServerAndOrganizationsRepository
import repositories.users.UsersRepositories

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds
    @ActivityScoped
    abstract fun bindMessagesRepository(messagesRepositoryImpl: MessagesRepositoryImpl): MessagesRepository

    @Binds
    @ActivityScoped
    abstract fun bindChannelsRepository(channelsRepositoryImpl: ChannelsRepositoryImpl): ChannelsRepository

    @Binds
    @ActivityScoped
    abstract fun bindUserRepository(userRepositoriesImp: UserRepositoryImp): UsersRepositories

    @Binds
    @ActivityScoped
    abstract fun bindDraftRepository(draftRepositoryImpl: DraftRepositoryImpl): DraftRepository

    @Binds
    @ActivityScoped
    abstract fun bindScheduledMessageRepository(scheduledMessageRepositoryImpl: ScheduledMessageRepositoryImpl): ScheduledMessageRepository

    @Binds
    @ActivityScoped
    abstract fun bindServerAndOrganizationsRepository(serverAndOrganizationsRepositoryImpl: ServerAndOrganizationsRepositoryImpl): ServerAndOrganizationsRepository

}