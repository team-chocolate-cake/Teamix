package com.chocolate.remote.datasource

import com.chocolate.entities.util.FireBaseException
import com.chocolate.remote.util.Constants
import com.chocolate.remote.util.tryToExecuteSuspendCall
import com.chocolate.repository.datasource.remote.TopicDataSource
import com.chocolate.repository.model.dto.topic.TopicDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TopicFirebaseDataSource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : TopicDataSource {
    override suspend fun createTopic(
        channelId: String,
        topic: TopicDto,
        organizationName: String
    ): String {
        return tryToExecuteSuspendCall {
            val topicRef = firebaseFirestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.CHANNEL)
                .document(channelId)
                .collection(Constants.TOPICS)
                .document()

            topicRef.set(topic.copy(topicId = topicRef.id))
                .await()

            return@tryToExecuteSuspendCall topicRef.id
        }
    }

    override suspend fun getTopicsInAChannel(
        channelId: String,
        organizationName: String
    ): Flow<List<TopicDto>> {
        return callbackFlow {
            val topics = firebaseFirestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.CHANNEL)
                .document(channelId)
                .collection(Constants.TOPICS)
                .addSnapshotListener { value, error ->
                    if (error != null)
                        throw FireBaseException(error.message)
                    else {
                        val topics = value?.toObjects<TopicDto>()
                        topics?.let { trySend(it) }
                    }
                }
            awaitClose { topics.remove() }
        }
    }
}