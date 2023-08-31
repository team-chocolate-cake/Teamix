package com.chocolate.usecases.channel

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.channel.MutingStatus
import com.chocolate.entities.channel.Topic
import repositories.ChannelsRepository
import repositories.UsersRepository
import javax.inject.Inject

class ManageChannelsUseCase @Inject constructor(
    private val channelsRepository: ChannelsRepository,
    private val usersRepositories: UsersRepository
) {

    private suspend fun getChannelSubscribers(channelId: Int): Boolean {
        return channelsRepository.getSubscriptionStatus(
            userId = usersRepositories.getRemoteCurrentUser().id,
            channelId = channelId
        )
    }

    suspend fun getSubscribedChannels(): List<Channel> {
        return channelsRepository.getSubscribedChannels()
    }

    suspend fun getStreamChannels()=channelsRepository.getStreamChannels()

     suspend fun getAllChannels(): List<Channel> {
        return channelsRepository.getChannels()
    }

    suspend fun searchChannels(channelName: String): List<Channel> {
        return getAllChannels().let { channels ->
            channels.filter { it.name.contains(channelName, true) }
        }
    }

    private suspend fun unsubscribeFromChannel(channelName: String) =
        channelsRepository.unsubscribeFromChannel(channelName)

    suspend fun getTopicsInChannel(channelId: Int): List<Topic> =
        channelsRepository.getTopicsInChannel(channelId)

    private suspend fun setTopicMuting(topic: String, status: MutingStatus, streamId: Int) =
        channelsRepository.setTopicMuting(topic, status, streamId)
}