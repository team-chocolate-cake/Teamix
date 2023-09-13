package repositories

import com.chocolate.entities.channel.Channel
import kotlinx.coroutines.flow.Flow

interface ChannelsRepository {
    suspend fun subscribeToChannel(
        channelName: String,
        usersId: List<String>,
        description: String?,
        isPrivate: Boolean
    ): Boolean

    suspend fun getChannelsInCurrentOrganization(): Flow<List<Channel>>

    suspend fun getStreamChannels(): Flow<List<Channel>>
    suspend fun getChannelsForCurrentMember(): Flow<List<Channel>>

}