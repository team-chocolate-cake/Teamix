package com.chocolate.remote.firebase

import com.chocolate.entities.user.User
import com.chocolate.repository.datastore.remote.UserDatabase
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
        val userCollection = firebaseFirestore.collection("users").document(user.id.toString())
        userCollection.set(userData).await()
    }
}