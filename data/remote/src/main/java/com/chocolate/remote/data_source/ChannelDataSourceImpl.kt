package com.chocolate.remote.data_source

import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.remote.firebase.util.Constants
import com.chocolate.remote.firebase.util.getRandomId
import com.chocolate.remote.firebase.util.tryToExecuteSuspendCall
import com.chocolate.repository.datastore.realtime.RealTimeDataSource
import com.chocolate.repository.datastore.realtime.model.ChannelDto
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

class FireBaseDataSource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : RealTimeDataSource {
    override suspend fun createChannel(
        channel:ChannelDto,
        organizationName:String
    ) {
        val channelId = getRandomId()
//        val channel = ChannelDto(
//            id = channelId.toString(),
//            name = channelName,
//            usersId = usersId,
//            isPrivate = isPrivate,
//            description = description,
//        )
        tryToExecuteSuspendCall {
//            firebaseFirestore.collection(Constants.BASE).document(organizationName).collection(Constants.CHANNEL).document(channelId.toString()).set(channel)
//                .await()

                firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organizationName)
                .collection(Constants.CHANNEL)
                .document(channelId.toString())
                .set(channel)
                .await()
        }
    }

//    override fun getChannels(organizationName): Flow<List<ChannelDto>> {
//        return callbackFlow {
//            val listener =
//                firebaseFirestore.collection(Constants.BASE).document(organizationName).collection(Constants.CHANNEL).addSnapshotListener { value, error ->
//                    if (error != null)
//                        throw TeamixException(error.message)
//                    val channels = value?.toObjects<ChannelDto>()
//                    channels?.let {
//                        trySend(it)
//                    }
//                }
//            awaitClose { listener.remove() }
//        }
//    }



    override suspend fun getChannelsInOrganizationByOrganizationName(organizationName: String): Flow<List<ChannelDto>?> {
        return callbackFlow {
            val organizationRef = firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organizationName)
                .collection(Constants.CHANNEL)
                .addSnapshotListener { channelsSnapshot, exception ->
                    if (exception != null)
                        throw TeamixException(exception.message)
                    val channels = channelsSnapshot?.toObjects<ChannelDto>()
                    channels?.let {
                        trySend(it)
                    }
                }
            awaitClose { organizationRef.remove() }
        }
    }
}