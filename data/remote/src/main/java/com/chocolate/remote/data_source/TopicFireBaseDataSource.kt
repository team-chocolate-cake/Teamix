package com.chocolate.remote.data_source

import com.chocolate.entities.exceptions.FireBaseException
import com.chocolate.remote.firebase.util.Constants.BASE
import com.chocolate.remote.firebase.util.Constants.CHANNEL
import com.chocolate.remote.firebase.util.Constants.TOPICS
import com.chocolate.remote.firebase.util.getRandomId
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
    private val firebaseFirestore: FirebaseFirestore,
) : TopicDataSource {
    override suspend fun createTopic(
        channelId: String,
        topic: TopicDto,
        organizationName: String
    ) {
        val topicId = getRandomId()
        val topicDto = TopicDto(
            topicId = topicId.toString(),
            content = topic.content
        )
        tryToExecuteSuspendCall {
            val topicRef = firebaseFirestore
                .collection(BASE)
                .document(organizationName)
                .collection(CHANNEL)
                .document(channelId)
                .collection(TOPICS)
                .document()

            topicRef.set(topicDto.copy(topicId = topicRef.id))
                .await()
        }
    }

    override suspend fun getTopicsInAChannel(
        channelId: String,
        organizationName: String
    ): Flow<List<TopicDto>> {
        return callbackFlow {
            val topics = firebaseFirestore
                .collection(BASE)
                .document(organizationName)
                .collection(CHANNEL)
                .document(channelId)
                .collection(TOPICS)
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