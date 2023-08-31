package com.chocolate.usecases.task

import com.chocolate.entities.task.Task
import repositories.UsersRepository
import javax.inject.Inject

class ManageTaskTeamUseCase  @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun setTeamTask(task: Task) {
        usersRepository.setTeamTask(task)
    }
    suspend fun getTeamTasks(): List<Task?> {
        return usersRepository.getTeamTask()
    }
}