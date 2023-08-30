package com.chocolate.repository.datastore.remote

import com.chocolate.entities.task.Task
import com.chocolate.entities.user.User

interface TaskRemoteDataSource {
    suspend fun setUsers(user: User)
    suspend fun getAllUser(): List<User?>
    suspend fun setTeamTask(task:Task)
    suspend fun getTeamTasks():List<Task?>
}