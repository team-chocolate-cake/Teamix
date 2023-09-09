package com.chocolate.remote.data_source

import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.remote.util.Constants
import com.chocolate.remote.util.getRandomId
import com.chocolate.remote.util.tryToExecuteSuspendCall
import com.chocolate.repository.datastore.remote.ChannelDataSource
import com.chocolate.repository.model.dto.channels.ChannelDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChannelDataSourceImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
): ChannelDataSource {

    override suspend fun createChannel(
        channelName: String,
        usersId: List<Int>,
        isPrivate: Boolean,
        description: String?,
    ) {
        val channelId = getRandomId()
        val channel = ChannelDto(
            id = channelId.toString(),
            name = channelName,
            description = description,
        )
        tryToExecuteSuspendCall {
            fireStore.collection(Constants.CHANNEL).document(channelId.toString()).set(channel)
                .await()
        }
    }

    override fun getChannels(): Flow<List<ChannelDto>> {
        return callbackFlow{
            val listener =
                fireStore.collection(Constants.CHANNEL).addSnapshotListener { value, error ->
                    error?.let { close(it) }
                    val channels = value?.toObjects<ChannelDto>()
                    channels?.let {
                        trySend(it)
                    }
                }
            awaitClose { listener.remove() }
        }
    }

}