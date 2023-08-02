package com.chocolate.repository.implementation.channels

import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.service.ChannelsDataSource
import repositories.channels.ChannelsRepository
import javax.inject.Inject

class ChannelsRepositoryImpl @Inject constructor(
    private val channelsDataSource: ChannelsDataSource,
) : ChannelsRepository, BaseRepository {
    override suspend fun getUserSubscriptions() {}

    override suspend fun addSubscribes() {}

    override suspend fun deleteSubscriber() {}

    override suspend fun getSubscriptionStatus() {}

    override suspend fun getAllSubscriber() {}

    override suspend fun updateSubscriptionSettings() {}

    override suspend fun getAllChannels() {}

    override suspend fun getChannelById() {}

    override suspend fun getChannelId() {}

    override suspend fun updateChannel() {}

    override suspend fun archiveChannel() {}

    override suspend fun getTopicsInChannel() {}

    override suspend fun setTopicMuting() {}

    override suspend fun updatePersonalPreferenceTopic() {}

    override suspend fun deleteTopic() {}

    override suspend fun addDefaultChannel() {}

    override suspend fun deleteDefaultChannel() {}
}