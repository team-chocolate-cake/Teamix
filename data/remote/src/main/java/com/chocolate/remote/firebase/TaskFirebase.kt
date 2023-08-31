package com.chocolate.remote.firebase

import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.task.Task
import com.chocolate.entities.uills.Empty
import com.chocolate.entities.user.User
import com.chocolate.repository.datastore.remote.TaskRemoteDataSource
import com.chocolate.repository.mappers.task.toEntity
import com.chocolate.repository.mappers.users.toEntity
import com.chocolate.repository.model.dto.task.TaskDataDto
import com.chocolate.repository.model.dto.users.response.UserDataDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TaskFirebase @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
): TaskRemoteDataSource {

    override suspend fun setUsers(user: UserDataDto) {
        firebaseFirestore.collection(USERS).document(user.id.toString()).set(user::class.java)
            .addOnFailureListener { throw NullDataException(it.message) }.await()
    }

    override suspend fun getAllUser(): List<UserDataDto?> = try {
        firebaseFirestore.collection(USERS)
            .get()
            .await()
            .documents
            .map { documentSnapshot ->
                documentSnapshot.toObject(UserDataDto::class.java)
            }
    } catch (e: Exception) {
        throw NullDataException(String.Empty)
    }

    override suspend fun setTeamTask(task: TaskDataDto) {
         firebaseFirestore.collection(TEAM_TASK).document(task.id.toString()).set(task::class.java)
             .addOnFailureListener { throw NullDataException(it.message) }.await()
    }
    override suspend fun getTeamTasks(): List<TaskDataDto?> = try {
        firebaseFirestore.collection(TEAM_TASK)
            .get()
            .await()
            .documents
            .map { documentSnapshot ->
                documentSnapshot.toObject(TaskDataDto::class.java)
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