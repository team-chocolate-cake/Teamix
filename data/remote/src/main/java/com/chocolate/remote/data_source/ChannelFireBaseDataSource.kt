package com.chocolate.remote.data_source

import android.util.Log
import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.remote.util.Constants
import com.chocolate.remote.util.tryToExecuteSuspendCall
import com.chocolate.repository.datastore.remote.ChannelRemoteDataSource
import com.chocolate.repository.model.dto.channels.ChannelDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChannelFireBaseDataSource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : ChannelRemoteDataSource {
    override suspend fun createChannel(
        channel: ChannelDto,
        organizationName: String
    ) {
        val channelDto = ChannelDto(
            id = channel.id!!,
            name = channel.name,
            membersId = channel.membersId,
            description = channel.description,
            isPrivate = channel.isPrivate,
        )
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.CHANNEL)
                .document(channel.id!!)
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
                .collection(Constants.CHANNEL)
                .whereArrayContains(Constants.USERS_ID, memberId)
                .addSnapshotListener { channelsSnapshot, exception ->
                    if (exception != null)
                        throw TeamixException(exception.message)
                    val channels = channelsSnapshot?.documents?.let { channelsDto ->
                        channelsDto.mapNotNull { it.toObject<ChannelDto>() }
                    }
                    Log.e("getChannelsForCurrentMember: ", channels.toString())
                    Log.e("CurrentMember: ", memberId)
                    trySend(channels ?: emptyList())
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

    override suspend fun getChannelInOrganizationByChannelName(
        organizationName: String,
        channelName: String
    ): ChannelDto? {
        return tryToExecuteSuspendCall {
            val channelRef = firebaseFirestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.CHANNEL)
                .whereEqualTo("name", channelName)
                .get()
                .await()
            channelRef.documents.firstOrNull()?.toObject<ChannelDto>()
        }
    }

}