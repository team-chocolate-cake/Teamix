package com.chocolate.remote.data_source

import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.remote.api.ChannelsService
import com.chocolate.remote.firebase.util.Constants
import com.chocolate.remote.firebase.util.getRandomId
import com.chocolate.remote.firebase.util.tryToExecuteSuspendCall
import com.chocolate.remote.wrapApiCall
import com.chocolate.repository.datastore.realtime.model.ChannelDto
import com.chocolate.repository.datastore.remote.ChannelRemoteDataSource
import com.chocolate.repository.model.dto.channels.response.AllStreamsDto
import com.chocolate.repository.model.dto.channels.response.AllSubscribersDto
import com.chocolate.repository.model.dto.channels.response.DefaultStreamDto
import com.chocolate.repository.model.dto.channels.response.StreamsByIdDto
import com.chocolate.repository.model.dto.channels.response.StreamsIdDto
import com.chocolate.repository.model.dto.channels.response.SubscribeToStreamDto
import com.chocolate.repository.model.dto.channels.response.SubscribedStreamDto
import com.chocolate.repository.model.dto.channels.response.SubscriptionStatusDto
import com.chocolate.repository.model.dto.channels.response.TopicsInStreamDto
import com.chocolate.repository.model.dto.channels.response.UnsubscribeFromStreamDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChannelFireBaseDataSource @Inject constructor(
    private val channelsService: ChannelsService,
    private val firebaseFirestore: FirebaseFirestore,
) : ChannelRemoteDataSource {
    override suspend fun createChannel(
        channel: ChannelDto,
        organizationName: String
    ) {
        val channelId = getRandomId()
        val channelDto = ChannelDto(
            id = channelId.toString(),
            name = channel.name,
            usersId = channel.usersId,
            description = channel.description,
            isPrivate = channel.isPrivate,
        )
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.CHANNEL)
                .document(channelId.toString())
                .set(channelDto)
                .await()
        }
    }

    override suspend fun getChannelsForCurrentMember(
        organizationName: String,
        memberId: String
    ): Flow<List<ChannelDto>> {
        return callbackFlow {
            val organizationRef = firebaseFirestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .document(memberId)
                .addSnapshotListener { memberSnapshot, exception ->
                    if (exception != null)
                        throw TeamixException(exception.message)
                  //  val channels = memberSnapshot?.toObjects<Memember>()
                    //channels?.let { trySend(it) }
                }
            awaitClose { organizationRef.remove() }
        }
    }


    override suspend fun getChannelsInOrganizationByOrganizationName(organizationName: String): Flow<List<ChannelDto>?> {
        return callbackFlow {
            val organizationRef = firebaseFirestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.CHANNEL)
                .addSnapshotListener { channelsSnapshot, exception ->
                    if (exception != null)
                        throw TeamixException(exception.message)
                    val channels = channelsSnapshot?.toObjects<ChannelDto>()
                    channels?.let { trySend(it) }
                }
            awaitClose { organizationRef.remove() }
        }
    }


    override suspend fun getSubscribedChannels(): SubscribedStreamDto {
        return wrapApiCall { channelsService.getSubscribedChannels() }
    }

    override suspend fun subscribeToChannels(
        channelName: String,
        description: String?,
        isPrivate: Boolean,
        usersId: String
    ): SubscribeToStreamDto {
        return wrapApiCall {
            channelsService.subscribeToChannels(
                channelsName = channelName,
                usersId = usersId,
                isPrivate = isPrivate
            )
        }
    }

    override suspend fun unsubscribeFromChannels(
        channelsName: List<String>
    ): UnsubscribeFromStreamDto {
        return wrapApiCall { channelsService.unsubscribeFromChannels(channelsName) }
    }

    override suspend fun getSubscriptionStatus(
        userId: Int, channelId: Int
    ): SubscriptionStatusDto {
        return wrapApiCall { channelsService.getSubscriptionStatus(userId, channelId) }
    }

    override suspend fun getSubscribersInChannel(channelId: Int): AllSubscribersDto {
        return wrapApiCall { channelsService.getSubscribersInChannel(channelId) }
    }

    override suspend fun getChannels(): AllStreamsDto {
        return wrapApiCall { channelsService.getChannels() }
    }

    override suspend fun getChannelById(channelId: Int): StreamsByIdDto {
        return wrapApiCall { channelsService.getChannelById(channelId) }
    }

    override suspend fun getChannelIdByName(channelName: String): StreamsIdDto {
        return wrapApiCall { channelsService.getChannelIdByName(channelName) }
    }

    override suspend fun updateStream(
        streamId: Int,
        description: String?,
        newName: String?,
        isPrivate: Boolean?,
        isWebPublic: Boolean?,
        historyPublicToSubscribers: Boolean?,
        streamPostPolicy: Int?,
        messageRetentionDays: String?,
        canRemoveSubscribersGroupId: Int?
    ): DefaultStreamDto {
        return wrapApiCall {
            channelsService.updateChannel(
                streamId,
                description,
                newName,
                isPrivate,
            )
        }
    }

    override suspend fun archiveChannel(channelId: Int): DefaultStreamDto {
        return wrapApiCall { channelsService.archiveChannel(channelId) }
    }

    override suspend fun getTopicsInChannel(channelId: Int): TopicsInStreamDto {
        return wrapApiCall { channelsService.getTopicsInChannel(channelId) }
    }

    override suspend fun setTopicMuting(
        topic: String,
        status: String,
        streamId: Int?,
    ): DefaultStreamDto {
        return wrapApiCall { channelsService.setTopicMuting(topic, status, streamId) }
    }

    override suspend fun updatePersonalPreferenceTopic(
        streamId: Int,
        topic: String,
        visibilityPolicy: Int
    ): DefaultStreamDto {
        return wrapApiCall {
            channelsService.updatePersonalPreferenceTopic(streamId, topic, visibilityPolicy)
        }
    }

    override suspend fun deleteTopic(channelId: Int, topicName: String): DefaultStreamDto {
        return wrapApiCall { channelsService.deleteTopic(channelId, topicName) }
    }

    override suspend fun addDefaultStream(channelId: Int): DefaultStreamDto {
        return wrapApiCall {
            channelsService.addDefaultChannel(channelId)
        }
    }

    override suspend fun deleteDefaultStream(channelId: Int): DefaultStreamDto {
        return wrapApiCall { channelsService.deleteDefaultChannel(channelId) }
    }
}