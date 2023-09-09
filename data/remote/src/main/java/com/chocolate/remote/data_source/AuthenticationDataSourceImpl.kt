package com.chocolate.remote.data_source

import com.chocolate.remote.firebase.util.tryToExecuteCall
import com.chocolate.remote.firebase.util.tryToExecuteSuspendCall
import com.chocolate.repository.datastore.remote.AuthenticationDataSource
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthenticationDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthenticationDataSource {

    override suspend fun registerUser(email: String, password: String): Unit =
        tryToExecuteSuspendCall { firebaseAuth.createUserWithEmailAndPassword(email, password) }

    override suspend fun loginUser(email: String, password: String): Unit =
        tryToExecuteSuspendCall { firebaseAuth.signInWithEmailAndPassword(email, password) }

    override fun getCurrentUserId(): String? =
        tryToExecuteCall { firebaseAuth.currentUser?.uid }

    override fun isUserLoggedIn(): Boolean =
        tryToExecuteCall { firebaseAuth.currentUser != null }

    override fun logoutUser() =
        tryToExecuteCall { firebaseAuth.signOut() }

}