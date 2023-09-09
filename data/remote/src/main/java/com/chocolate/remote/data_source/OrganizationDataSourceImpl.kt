package com.chocolate.remote.data_source

import com.chocolate.entities.Organization
import com.chocolate.entities.channel.Channel
import com.chocolate.remote.firebase.util.Constants
import com.chocolate.remote.firebase.util.tryToExecuteSuspendCall
import com.chocolate.repository.datastore.remote.OrganizationRemoteDataSource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class OrganizationDataSourceImpl @Inject constructor(
    private val firebase: FirebaseFirestore
) : OrganizationRemoteDataSource {
    override suspend fun getOrganizationById(id: String): Organization {
        return tryToExecuteSuspendCall {
            suspendCoroutine { cont ->
                firebase.collection(Constants.ORGANIZATION).document(id).get()
                    .addOnSuccessListener { doc ->
                        doc?.toObject<Organization>()?.let { cont.resume(it) }
                    }
                    .addOnFailureListener {
                        cont.resumeWithException(it)
                    }
            }
        }
    }

    override suspend fun getOrganizaionsByMemberId(id: String): List<Organization> {
        TODO("Not yet implemented")
    }

    override suspend fun addOrganization(organization: Organization) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOrganizationbyId(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateOrganization(organization: Organization) {
        TODO("Not yet implemented")
    }

    override suspend fun addChannel(organizationId: Long, channel: Channel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteChannelById(channelId: Long) {
        TODO("Not yet implemented")
    }


}