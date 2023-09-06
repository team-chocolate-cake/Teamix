package com.chocolate.remote.data_source

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.organization.Organization
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
    private val firebaseFirestore: FirebaseFirestore
) : MemberDataSource {
    /*override suspend fun getMemberById(id: String): Member? {
        return tryToExecuteSuspendCall {
            val memberSnapshot =
                firebaseFirestore.collection(Constants.MEMBERS).document(id).get().await()
            memberSnapshot.toObject<Member>()
        }
    }

    override suspend fun getMembersInChannelByChannelId(id: String): Flow<List<Member>> {
        return callbackFlow {
            tryToExecuteSuspendCall {
                val channelRef = firebaseFirestore
                    .collection(Constants.CHANNEL)
                    .document(id)
                    .addSnapshotListener { channelSnapshot, exception ->
                        exception?.let { close(it) }

                        channelSnapshot?.toObject<Channel>()?.members?.let { ids ->
                            firebaseFirestore
                                .collection(Constants.MEMBERS)
                                .whereIn("id", ids)
                                .addSnapshotListener { membersSnapshot, membersException ->
                                    membersException?.let { close(it) }

                                    val members = membersSnapshot?.documents?.mapNotNull {
                                        it.toObject<Member>()
                                    } ?: emptyList()
                                    trySend(members)
                                }
                        }
                    }
                awaitClose { channelRef.remove() }
            }
        }
    }

    override suspend fun getMembersInOrganizationByOrganizationId(id: String): Flow<List<Member>> {
        return callbackFlow {
            tryToExecuteSuspendCall {
                val organizationRef = firebaseFirestore
                    .collection(Constants.ORGANIZATION)
                    .document(id)
                    .addSnapshotListener { organizationSnapshot, exception ->
                        exception?.let { close(it) }
                        organizationSnapshot?.toObject<Organization>()?.members?.let { ids ->
                            firebaseFirestore
                                .collection(Constants.MEMBERS)
                                .whereIn("id", ids)
                                .addSnapshotListener { membersSnapshot, membersException ->
                                    membersException?.let { close(it) }
                                    val members = membersSnapshot?.documents?.mapNotNull {
                                        it.toObject<Member>()
                                    } ?: emptyList()
                                    trySend(members)
                                }
                        }
                    }
                awaitClose { organizationRef.remove() }
            }
        }
    }

    override suspend fun deactivateMember(memberId: String) {
        firebaseFirestore
            .collection(Constants.MEMBERS)
            .document(memberId)
            .update("isActive", false)
            .await()
    }

    override suspend fun updateMember(member: Member) {
        firebaseFirestore
            .collection(Constants.MEMBERS)
            .document(member.id)
            .set(member)
            .await()
    }

    override suspend fun changeRole(memberId: String, newRole: UserRole) {
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.MEMBERS)
                .document(memberId)
                .update("role", newRole)
                .await()
        }
    }*/
    override suspend fun getMemberById(id: String): Member? {
        TODO("Not yet implemented")
    }

    override suspend fun getMembersInChannelByChannelId(id: String): Flow<List<Member>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMembersInOrganizationByOrganizationId(id: String): Flow<List<Member>> {
        TODO("Not yet implemented")
    }

    override suspend fun deactivateMember(memberId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMember(member: Member) {
        TODO("Not yet implemented")
    }

    override suspend fun changeRole(memberId: String, newRole: UserRole) {
        TODO("Not yet implemented")
    }

    override suspend fun addMembersInChannel(members: List<String>, channelId: String) {
        TODO("Not yet implemented")
    }

}