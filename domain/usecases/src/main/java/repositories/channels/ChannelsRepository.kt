package repositories.channels

interface ChannelsRepository {
    suspend fun getUserSubscriptions()
    suspend fun addSubscribes()
    suspend fun deleteSubscriber()
    suspend fun getSubscriptionStatus()
    suspend fun getAllSubscriber()
    suspend fun updateSubscriptionSettings()
    suspend fun getAllChannels()
    suspend fun getChannelById()
    suspend fun getChannelId()
    suspend fun updateChannel()
    suspend fun archiveChannel()
    suspend fun getTopicsInChannel()
    suspend fun setTopicMuting()
    suspend fun updatePersonalPreferenceTopic()
    suspend fun deleteTopic()
    suspend fun addDefaultChannel()
    suspend fun deleteDefaultChannel()
}