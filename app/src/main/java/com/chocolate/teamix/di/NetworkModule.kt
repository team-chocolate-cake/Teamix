package com.chocolate.teamix.di

import com.chocolate.remote.AuthInterceptor
import com.chocolate.remote.channels.ChannelsImpl
import com.chocolate.remote.channels.service.ChannelsService
import com.chocolate.remote.drafts.DraftsImpl
import com.chocolate.remote.drafts.service.DraftService
import com.chocolate.remote.messages.MessagesImpl
import com.chocolate.remote.messages.service.MessageService
import com.chocolate.remote.scheduled_message.ScheduledMessageImpl
import com.chocolate.remote.scheduled_message.service.ScheduledMessageService
import com.chocolate.remote.server_and_organizations.OrganizationsImpl
import com.chocolate.remote.server_and_organizations.service.OrganizationService
import com.chocolate.remote.users.UsersImpl
import com.chocolate.remote.users.service.UserService
import com.chocolate.repository.service.IChannelsService
import com.chocolate.repository.service.IDraftService
import com.chocolate.repository.service.IMessageService
import com.chocolate.repository.service.IOrganizationService
import com.chocolate.repository.service.IScheduledMessageService
import com.chocolate.repository.service.IUserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(client: OkHttpClient, factory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl("")
            .client(client)
            .addConverterFactory(factory)
            .build()

    @Singleton
    @Provides
    fun provideParser(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideDraftService(retrofit: Retrofit): DraftService =
        retrofit.create(DraftService::class.java)

    @Singleton
    @Provides
    fun provideChannelService(retrofit: Retrofit): ChannelsService =
        retrofit.create(ChannelsService::class.java)

    @Singleton
    @Provides
    fun provideMessageService(retrofit: Retrofit): MessageService =
        retrofit.create(MessageService::class.java)

    @Singleton
    @Provides
    fun provideScheduledMessageService(retrofit: Retrofit): ScheduledMessageService =
        retrofit.create(ScheduledMessageService::class.java)

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideOrganizationService(retrofit: Retrofit): OrganizationService =
        retrofit.create(OrganizationService::class.java)

    @Singleton
    @Provides
    fun provideChannels(channelsService: ChannelsService): IChannelsService {
        return ChannelsImpl(channelsService)
    }


    @Singleton
    @Provides
    fun provideOrganization(organizationService: OrganizationService): IOrganizationService {
        return OrganizationsImpl(organizationService)
    }


    @Singleton
    @Provides
    fun provideDrafts(draftService: DraftService): IDraftService {
        return DraftsImpl(draftService)
    }


    @Singleton
    @Provides
    fun provideMessages(messageService: MessageService): IMessageService {
        return MessagesImpl(messageService)
    }


    @Singleton
    @Provides
    fun provideScheduledMessage(scheduledMessageService: ScheduledMessageService): IScheduledMessageService {
        return ScheduledMessageImpl(scheduledMessageService)
    }


    @Singleton
    @Provides
    fun provideUsers(userService: UserService): IUserService {
        return UsersImpl(userService)
    }

}