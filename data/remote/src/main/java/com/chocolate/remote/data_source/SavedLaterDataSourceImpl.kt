package com.chocolate.remote.data_source

import android.util.Log
import com.chocolate.remote.util.Constants
import com.chocolate.remote.util.tryToExecuteSuspendCall
import com.chocolate.repository.datastore.remote.SavedLaterDataSource
import com.chocolate.repository.model.dto.message.SavedLaterMessageDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SavedLaterDataSourceImpl @Inject constructor(
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
            Log.e("deleteSavedLaterMessageById: ", savedLaterMessageId)
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
}