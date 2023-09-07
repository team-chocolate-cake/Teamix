package com.chocolate.remote.data_source

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.Organization
import com.chocolate.entities.user.Member
import com.chocolate.entities.user.UserRole
import com.chocolate.remote.firebase.util.Constants
import com.chocolate.remote.firebase.util.tryToExecuteSuspendCall
import com.chocolate.repository.datastore.remote.MemberDataSource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MemberDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : MemberDataSource {

    override suspend fun getMemberInOrganizationById(
        memberId: String,
        organizationName: String
    ): Member? {
        return tryToExecuteSuspendCall {
            val memberRef = firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .document(memberId)
                .get()
                .await()
            memberRef.toObject<Member>()
        }
    }

    override suspend fun getMembersInChannelByChannelId(
        organizationName: String,
        channelId: String
    ): Flow<List<Member>> {
        return callbackFlow {
            val organizationRef = firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organizationName)
                .addSnapshotListener { organizationSnapshot, exception ->
                    exception?.let { close(it) }
                    organizationSnapshot?.toObject<Organization>()?.members?.let { members ->
                        trySend(members.filter { it.channels.contains(channelId) })
                    }
                }
            awaitClose { organizationRef.remove() }
        }
    }

    override suspend fun getMembersInOrganizationByOrganizationName(organizationName: String): Flow<List<Member>?> {
        return callbackFlow {
            val organizationRef = firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organizationName)
                .addSnapshotListener { organizationSnapshot, exception ->
                    exception?.let { close(it) }
                    trySend(organizationSnapshot?.toObject<Organization>()?.members)
                }
            awaitClose { organizationRef.remove() }
        }
    }

    override suspend fun getChannelsInOrganizationByOrganizationName(organizationName: String): Flow<List<Channel>?> {
        return callbackFlow {
            val organizationRef = firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organizationName)
                .addSnapshotListener { organizationSnapshot, exception ->
                    exception?.let { close(it) }
                    trySend(organizationSnapshot?.toObject<Organization>()?.channels)
                }
            awaitClose { organizationRef.remove() }
        }
    }

    override suspend fun deactivateMember(organizationName: String, memberId: String) {
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.MEMBERS)
                .document(memberId)
                .update("isActive", false)
                .await()
        }
    }

    override suspend fun updateMember(organizationName: String, member: Member) {
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .document(member.id)
                .set(member)
                .await()
        }
    }

    override suspend fun changeRole(organizationName: String, memberId: String, newRole: UserRole) {
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .document(memberId)
                .update("role", newRole)
                .await()
        }
    }

    override suspend fun addMembersInChannel(
        organizationName: String,
        members: List<String>,
        channelId: String
    ) {
        val channelDocRef = firebaseFirestore
            .collection(Constants.ORGANIZATION)
            .document(organizationName)
            .collection(Constants.CHANNEL)
            .document(channelId)
        val channelRef = channelDocRef
            .get()
            .await()
        channelRef.toObject<Channel>()?.let { channel ->
            val oldMembersId = channel.membersId.toMutableList()
            oldMembersId.addAll(members)
            val newMembersId = oldMembersId.toList()
            val newChannel = channel.copy(membersId = newMembersId)
            channelDocRef
                .set(newChannel)
                .await()
        }
    }

    override suspend fun getMemberInOrganizationByEmail(
        organizationName: String,
        email: String
    ): Member? {
        return tryToExecuteSuspendCall {
            val memberRef = firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .whereEqualTo("email", email)
                .get()
                .await()
            memberRef.documents.firstOrNull()?.toObject<Member>()
        }
    }

}