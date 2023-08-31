package com.chocolate.remote.firebase

import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.repository.datastore.realtime.model.ChannelDto
import com.chocolate.remote.firebase.util.Constants
import com.chocolate.remote.firebase.util.getRandomId
import com.chocolate.remote.firebase.util.wrapRealTimeCall
import com.chocolate.repository.datastore.realtime.RealTimeDataSource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireBaseDataSource @Inject constructor(
    private val fireStore: FirebaseFirestore,
) : RealTimeDataSource {
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
            usersId = usersId,
            isPrivate = isPrivate,
            description = description,
        )
        wrapRealTimeCall {
            fireStore.collection(Constants.CHANNEL).document(channelId.toString()).set(channel).await()
        }
    }

    override fun getChannels(): Flow<List<ChannelDto>> {
        return callbackFlow {
            val listener =
                fireStore.collection(Constants.CHANNEL).addSnapshotListener { value, error ->
                    if (error != null)
                        throw TeamixException(error.message)
                    val channels = value?.toObjects<ChannelDto>()
                    channels?.let {
                        trySend(it)
                    }
                }
            awaitClose { listener.remove() }
        }
    }
}