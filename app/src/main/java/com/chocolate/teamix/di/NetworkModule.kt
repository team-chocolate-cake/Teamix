package com.chocolate.teamix.di

import com.chocolate.remote.call.service.CallService
import com.chocolate.remote.channels.service.ChannelsService
import com.chocolate.remote.chat.service.ChatService
import com.chocolate.remote.groups.service.GroupsService
import com.chocolate.remote.live_text.service.LiveTextService
import com.chocolate.remote.meet.service.MeetService
import com.chocolate.remote.notification.service.NotificationService
import com.chocolate.remote.progress_task.service.ProgressTaskService
import com.chocolate.remote.stories.service.StoriesService
import com.chocolate.remote.trends.service.TrendsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

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
    fun provideCallService(retrofit: Retrofit): CallService =
        retrofit.create(CallService::class.java)

    @Singleton
    @Provides
    fun provideChannelService(retrofit: Retrofit): ChannelsService =
        retrofit.create(ChannelsService::class.java)

    @Singleton
    @Provides
    fun provideChatService(retrofit: Retrofit): ChatService =
        retrofit.create(ChatService::class.java)

    @Singleton
    @Provides
    fun provideGroupsService(retrofit: Retrofit): GroupsService =
        retrofit.create(GroupsService::class.java)

    @Singleton
    @Provides
    fun provideLiveTextService(retrofit: Retrofit): LiveTextService =
        retrofit.create(LiveTextService::class.java)

    @Singleton
    @Provides
    fun provideMeetService(retrofit: Retrofit): MeetService =
        retrofit.create(MeetService::class.java)

    @Singleton
    @Provides
    fun provideNotificationService(retrofit: Retrofit): NotificationService =
        retrofit.create(NotificationService::class.java)

    @Singleton
    @Provides
    fun provideProgressTaskService(retrofit: Retrofit): ProgressTaskService =
        retrofit.create(ProgressTaskService::class.java)

    @Singleton
    @Provides
    fun provideStoriesService(retrofit: Retrofit): StoriesService =
        retrofit.create(StoriesService::class.java)

    @Singleton
    @Provides
    fun provideTrendsService(retrofit: Retrofit): TrendsService =
        retrofit.create(TrendsService::class.java)

}