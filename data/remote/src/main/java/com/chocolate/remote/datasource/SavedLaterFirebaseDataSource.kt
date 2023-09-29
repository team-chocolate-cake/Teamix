package com.chocolate.remote.datasource

import com.chocolate.remote.util.Constants
import com.chocolate.remote.util.tryToExecuteSuspendCall
import com.chocolate.repository.datasource.remote.SavedLaterDataSource
import com.chocolate.repository.model.dto.message.SavedLaterMessageDto
import com.chocolate.repository.model.dto.topic.TopicDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SavedLaterFirebaseDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : SavedLaterDataSource {
    override suspend fun addSavedLaterMessage(
        organizationName: String,
        savedLaterMessage: SavedLaterMessageDto
    ) {
        tryToExecuteSuspendCall {
            firestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .document(savedLaterMessage.senderId!!)
                .collection(Constants.SAVED_LATER)
                .document(savedLaterMessage.id!!)
                .set(savedLaterMessage)
                .await()
        }
    }

    override suspend fun getSavedLaterMessages(
        organizationName: String,
        memberId: String
    ): Flow<List<SavedLaterMessageDto>> {
        return callbackFlow {
            val listener = firestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .document(memberId)
                .collection(Constants.SAVED_LATER)
                .addSnapshotListener { savedLaterMessagesSnapShot, error ->
                    error?.let { close(it) }
                    val savedLaterMessage = savedLaterMessagesSnapShot?.documents?.mapNotNull {
                        it.toObject<SavedLaterMessageDto>()
                    }
                    savedLaterMessage?.let { trySend(it) }
                }
            awaitClose { listener.remove() }
        }
    }

    override suspend fun deleteSavedLaterMessageById(
        organizationName: String,
        memberId: String,
        savedLaterMessageId: String
    ) {
        tryToExecuteSuspendCall {
            firestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .document(memberId)
                .collection(Constants.SAVED_LATER)
                .document(savedLaterMessageId)
                .delete()
                .await()
        }
    }

    override suspend fun addSavedTopic(
        organizationName: String,
        topic: TopicDto,
        memberId: String,
    ) {
        tryToExecuteSuspendCall {
            firestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .document(memberId)
                .collection(Constants.SAVED_TOPICS)
                .document(topic.topicId!!)
                .set(topic)
                .await()
        }
    }

    override suspend fun getSavedTopics(
        organizationName: String,
        memberId: String
    ): Flow<List<TopicDto>> {
        return callbackFlow {
            val listener = firestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .document(memberId)
                .collection(Constants.SAVED_TOPICS)
                .addSnapshotListener { savedTopicsSnapShot, error ->
                    error?.let { close(it) }
                    val savedTopics = savedTopicsSnapShot?.documents?.mapNotNull {
                        it.toObject<TopicDto>()
                    }
                    savedTopics?.let { trySend(it) }
                }
            awaitClose { listener.remove() }
        }
    }

    override suspend fun deleteSavedTopicById(
        organizationName: String,
        memberId: String,
        topicId: String
    ) {
        tryToExecuteSuspendCall {
            firestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .document(memberId)
                .collection(Constants.SAVED_TOPICS)
                .document(topicId)
                .delete()
                .await()
        }
    }
}