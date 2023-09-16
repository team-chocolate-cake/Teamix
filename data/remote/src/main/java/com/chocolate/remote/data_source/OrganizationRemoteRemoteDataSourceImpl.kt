package com.chocolate.remote.data_source

import android.net.Uri
import com.chocolate.entities.uills.getRandomId
import com.chocolate.remote.util.Constants
import com.chocolate.remote.util.Constants.ORGANIZATION_IMAGES_PATH
import com.chocolate.remote.util.Constants.PROFILE_IMAGES_PATH
import com.chocolate.remote.util.tryToExecuteSuspendCall
import com.chocolate.repository.datastore.remote.OrganizationRemoteDataSource
import com.chocolate.repository.model.dto.member.MemberDto
import com.chocolate.repository.model.dto.organization.OrganizationDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OrganizationRemoteRemoteDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
) : OrganizationRemoteDataSource {

    override suspend fun getOrganizationByName(organizationName: String): OrganizationDto? {
        return tryToExecuteSuspendCall {
            val organizationsRef = firebaseFirestore
                .collection(Constants.BASE)
                .document(organizationName)
                .get()
                .await()

            organizationsRef.toObject<OrganizationDto>()
        }
    }

    override suspend fun createOrganization(organization: OrganizationDto) {
        tryToExecuteSuspendCall {
            val imageUri = uploadImage(ORGANIZATION_IMAGES_PATH, organization.imageUrl!!)
            val newOrganization = organization.copy(imageUrl = imageUri)
            firebaseFirestore
                .collection(Constants.BASE)
                .document(newOrganization.name!!)
                .set(newOrganization)
                .await()
        }
    }

    override suspend fun deleteOrganizationByOrganizationName(organizationName: String) {
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.BASE)
                .document(organizationName)
                .delete()
                .await()
        }
    }

    override suspend fun updateOrganization(organization: OrganizationDto) {
        tryToExecuteSuspendCall {
            firebaseFirestore
                .collection(Constants.BASE)
                .document(organization.name!!)
                .set(organization)
                .await()
        }
    }

    override suspend fun addMemberInOrganization(member: MemberDto, organizationName: String) {
        tryToExecuteSuspendCall {
            val imageUri = uploadImage(PROFILE_IMAGES_PATH, member.imageUrl!!)
            val newMember = member.copy(imageUrl = imageUri)
            firebaseFirestore
                .collection(Constants.BASE)
                .document(organizationName)
                .collection(Constants.MEMBERS)
                .document(newMember.id!!)
                .set(newMember)
                .await()
        }
    }

    private suspend fun uploadImage(path: String, imageUri: String): String {
        val storageRef = firebaseStorage.reference.child("${path}/${getRandomId()}")
        storageRef.putFile(Uri.parse(imageUri)).await()
        val downloadUrl = storageRef.downloadUrl.await()
        return downloadUrl.toString()
    }

}