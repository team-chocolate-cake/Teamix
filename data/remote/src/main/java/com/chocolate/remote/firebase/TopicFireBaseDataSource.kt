package com.chocolate.remote.firebase

import com.chocolate.entities.exceptions.FireBaseException
import com.chocolate.remote.firebase.util.Constants.CHANNEL
import com.chocolate.remote.firebase.util.Constants.ORG
import com.chocolate.remote.firebase.util.Constants.TOPICS
import com.chocolate.remote.firebase.util.tryToExecuteSuspendCall
import com.chocolate.repository.datastore.realtime.TopicDataSource
import com.chocolate.repository.datastore.realtime.model.TopicDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TopicFireBaseDataSource @Inject constructor(
    private val fireStore: FirebaseFirestore,
) : TopicDataSource {
    override suspend fun createTopic(
        channelName: String,
        topic: TopicDto,
        organizationName: String
    ) {
        tryToExecuteSuspendCall {
            fireStore.collection(ORG).document(organizationName).collection(CHANNEL)
                .document(channelName).collection(TOPICS).add(topic).await()
        }
    }

    override suspend fun getTopicsInAChannel(
        channelName: String,
        organizationName: String
    ): Flow<List<TopicDto>> {
        return callbackFlow {
            val topics = fireStore.collection(ORG).document(organizationName).collection(CHANNEL)
                .document(channelName).collection(TOPICS).addSnapshotListener { value, error ->
                    if (error != null)
                        throw FireBaseException(error.message)
                    else {
                        val topics = value?.toObjects<TopicDto>()
                        topics?.let {
                            trySend(it)
                        }
                    }
                }
            awaitClose { topics.remove() }
        }
    }
}