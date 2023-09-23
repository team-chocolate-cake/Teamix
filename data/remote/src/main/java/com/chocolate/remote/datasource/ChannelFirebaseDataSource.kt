package com.chocolate.remote.datasource

import com.chocolate.remote.util.Constants
import com.chocolate.remote.util.tryToExecuteSuspendCall
import com.chocolate.repository.datasource.remote.ChannelDataSource
import com.chocolate.repository.model.dto.channel.ChannelDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChannelFirebaseDataSource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : ChannelDataSource {
    override suspend fun createChannel(
        channel: ChannelDto,
        organizationName: String
    ) {
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.CHANNEL)
                .document(channel.id!!)
                .set(channel)
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
                    exception?.let { close(it) }
                    channelsSnapshot?.documents?.let { channelsDto ->
                        channelsDto.mapNotNull { it.toObject<ChannelDto>() }
                    }.also {
                        trySend(it ?: emptyList())
                    }
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
                    exception?.let { close(it) }
                    channelsSnapshot?.toObjects<ChannelDto>()?.let { channelsDto ->
                        trySend(channelsDto)
                    }
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