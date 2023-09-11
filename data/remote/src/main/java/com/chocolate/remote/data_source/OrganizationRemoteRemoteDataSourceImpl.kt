package com.chocolate.remote.data_source

import android.util.Log
import com.chocolate.remote.util.Constants
import com.chocolate.remote.util.tryToExecuteSuspendCall
import com.chocolate.repository.datastore.remote.OrganizationRemoteDataSource
import com.chocolate.repository.model.dto.member.MemberDto
import com.chocolate.repository.model.dto.organization.OrganizationDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OrganizationRemoteRemoteDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : OrganizationRemoteDataSource {

    override suspend fun getOrganizationByName(organizationName: String): OrganizationDto? {
        return tryToExecuteSuspendCall {
            val organizationsRef = firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organizationName)
                .get()
                .await()

            organizationsRef.toObject<OrganizationDto>()?.toString()?.let { Log.e("onError: ", it) }
            organizationsRef.toObject<OrganizationDto>()
        }
    }

    override suspend fun createOrganization(organization: OrganizationDto) {
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organization.name!!)
                .set(organization)
                .await()
        }
    }

    override suspend fun deleteOrganizationByOrganizationName(organizationName: String) {
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organizationName)
                .delete()
                .await()
        }
    }

    override suspend fun updateOrganization(organization: OrganizationDto) {
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organization.name!!)
                .set(organization)
                .await()
        }
    }

    override suspend fun addMemberInOrganization(member: MemberDto, organizationName: String) {
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.ORGANIZATION)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .document(member.id!!)
                .set(member)
                .await()
        }
    }
}