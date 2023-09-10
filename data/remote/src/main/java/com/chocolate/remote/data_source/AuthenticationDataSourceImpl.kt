package com.chocolate.remote.data_source

import com.chocolate.remote.util.tryToExecuteCall
import com.chocolate.remote.util.tryToExecuteSuspendCall
import com.chocolate.repository.datastore.remote.AuthenticationDataSource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthenticationDataSource {

    override suspend fun registerUser(email: String, password: String): Unit =
        tryToExecuteSuspendCall { firebaseAuth.createUserWithEmailAndPassword(email, password) }

    override suspend fun loginUser(email: String, password: String): Unit =
        tryToExecuteSuspendCall { firebaseAuth.signInWithEmailAndPassword(email, password).await() }

    override fun getCurrentUserId(): String? =
        tryToExecuteCall { firebaseAuth.currentUser?.uid }

    override fun isUserLoggedIn(): Boolean =
        tryToExecuteCall { firebaseAuth.currentUser != null }

    override fun logoutUser() =
        tryToExecuteCall { firebaseAuth.signOut() }

}