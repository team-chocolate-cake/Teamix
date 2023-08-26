package repositories

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.channel.MutingStatus
import com.chocolate.entities.channel.Topic

interface ChannelsRepository {
    suspend fun getSubscribedChannels(): List<Channel>

    suspend fun subscribeToChannel(
        channelName: String,
        usersId: List<Int>,
        description: String?,
        isPrivate: Boolean
    ): Boolean

    suspend fun unsubscribeFromChannel(channelName: String): Boolean

    suspend fun getSubscriptionStatus(userId: Int, channelId: Int): Boolean

    suspend fun getSubscribersByChannelId(channelId: Int): List<Int>

    suspend fun getChannels(): List<Channel>

    suspend fun getChannelById(channelId: Int): Channel?

    suspend fun getChannelIdByName(channel: String): Int

    suspend fun updateChannel(
        streamId: Int,
        description: String?,
        newName: String?,
        isPrivate: Boolean?,
        isWebPublic: Boolean?,
        historyPublicToSubscribers: Boolean?,
        streamPostPolicy: Int?,
        messageRetentionDays: String?,
        canRemoveSubscribersGroupId: Int?
    ): Boolean

    suspend fun archiveChannel(channelId: Int): Boolean

    suspend fun getTopicsInChannel(channelId: Int): List<Topic>

    suspend fun setTopicMuting(
        topic: String,
        status: MutingStatus,
        streamId: Int? = null,
    ): Boolean

    suspend fun updatePersonalPreferenceTopic(
        channelId: Int,
        topic: String,
        visibilityPolicy: Int
    ): Boolean

    suspend fun deleteTopic(channelId: Int, topicName: String): Boolean

    suspend fun addDefaultChannel(channelId: Int): Boolean

    suspend fun deleteDefaultChannel(channelId: Int): Boolean
}