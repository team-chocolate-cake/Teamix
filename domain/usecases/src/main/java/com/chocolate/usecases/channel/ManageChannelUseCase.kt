package com.chocolate.usecases.channel

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.channel.MutingStatus
import com.chocolate.entities.channel.Topic
import com.chocolate.entities.exceptions.EmptyTopicContentException
import com.chocolate.entities.exceptions.EmptyTopicNameException
import repositories.ChannelsRepository
import repositories.MessagesRepository
import repositories.UsersRepository
import javax.inject.Inject

class ManageChannelsUseCase @Inject constructor(
    private val channelsRepository: ChannelsRepository,
    private val messagesRepository: MessagesRepository,
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

    private suspend fun getAllChannels(): List<Channel> {
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

    suspend fun createNewTopic(topicName: String, channelName: String, content: String):Int {
        if (topicName.isBlank())
            throw EmptyTopicNameException
        else if (content.isBlank())
            throw EmptyTopicContentException
        return messagesRepository.sendStreamMessage("stream", channelName, topicName , content)
    }

}