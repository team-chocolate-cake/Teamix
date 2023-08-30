package com.chocolate.remote.firebase

import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.uills.Empty
import com.chocolate.entities.user.User
import com.chocolate.repository.mappers.users.toEntity
import com.chocolate.repository.model.dto.users.response.UserDataDto
import com.chocolate.repository.repository.UserDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseUsersDatabase @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
): UserDatabase {

    override suspend fun setUsers(user: User) {
        val userData = hashMapOf(
            "id" to user.id,
            "name" to user.fullName,
            "role" to user.role,
            "email" to user.email,
            "imageUrl" to user.imageUrl
        )
        val userCollection = firebaseFirestore.collection(USERS).document(user.id.toString())
        userCollection.set(userData).await()
    }

    override suspend fun getAllUser(): List<User?> = try {
        firebaseFirestore.collection(USERS)
            .get()
            .await()
            .documents
            .map { documentSnapshot ->
                documentSnapshot.toObject(UserDataDto::class.java)?.toEntity()
            }
    } catch (e: Exception) {
        throw NullDataException(String.Empty)
    }

    companion object {
        private const val USERS = "users"
        private const val ORGANIZATION_TASK = "organization_task"
        private const val TEAM_TASK = "team_task"
    }
}