package com.chocolate.repository.datastore.remote

import com.chocolate.entities.task.Task
import com.chocolate.entities.user.User
import com.chocolate.repository.model.dto.task.TaskDataDto
import com.chocolate.repository.model.dto.users.response.UserDataDto

interface TaskRemoteDataSource {
    suspend fun setUsers(user: UserDataDto)
    suspend fun getAllUser(): List<UserDataDto?>
    suspend fun setTeamTask(task:TaskDataDto)
    suspend fun getTeamTasks():List<TaskDataDto?>
}