package com.chocolate.remote.firebase

import com.chocolate.entities.exceptions.FireBaseException
import com.chocolate.entities.messages.KareemMessage
import com.chocolate.entities.messages.Topic
import com.chocolate.remote.firebase.util.Constants.CHANNEL
import com.chocolate.remote.firebase.util.Constants.MESSAGES
import com.chocolate.remote.firebase.util.Constants.BASE
import com.chocolate.remote.firebase.util.Constants.TOPICS
import com.chocolate.remote.firebase.util.tryToExecuteSuspendCall
import com.chocolate.repository.datastore.realtime.TopicDataSource
import com.chocolate.repository.datastore.realtime.model.TopicDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
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
            fireStore.collection(BASE).document(organizationName).collection(CHANNEL)
                .document(channelName).collection(TOPICS).add(topic).await()


            fireStore.collection(BASE).document(organizationName).collection(CHANNEL)
                .document(channelName).collection(TOPICS).document("4T6DWHFRyuNx0gqI3p9S")
                .collection(MESSAGES).add(KareemMessage()).await()


        }
    }

    override suspend fun getTopicsInAChannel(
        channelName: String,
        organizationName: String
    ): Flow<List<Topic>> {
        return callbackFlow {
            val topics = fireStore.collection(BASE).document(organizationName).collection(CHANNEL)
                .document(channelName).collection(TOPICS).addSnapshotListener { value, error ->
                    if (error != null)
                        throw FireBaseException(error.message)
                    else {
                        val topics = value?.toObjects<Topic>()
                        topics?.let {
                            trySend(it)
                        }
                    }
                }
            awaitClose { topics.remove() }
        }
    }

    override suspend fun getMessagesInATopic(channelId: String, topicId: String): Flow<TopicDto> {
        return callbackFlow {

            val topicDocumentRef =
                fireStore.collection(BASE).document("teamixOrganization").collection(CHANNEL)
                    .document(channelId).collection(TOPICS).document(topicId)

            val topicMessagesCollectionRef = topicDocumentRef.collection(MESSAGES)

            val listener = topicDocumentRef.addSnapshotListener { value, error ->
                if (error != null)
                    throw FireBaseException(error.message)
                else {
                    val topic = value?.toObject<TopicDto>()


                    topicMessagesCollectionRef.get().addOnSuccessListener { messageQuerySnapShot ->
                        if (!messageQuerySnapShot.isEmpty) {
                            val messages = messageQuerySnapShot.toObjects<KareemMessage>()
                            val finalTopic = topic?.copy(messages = messages)
                            finalTopic?.let {
                                trySend(it)
                            }
                        }
                    }
                }
            }
            awaitClose { listener.remove() }
        }
    }
}