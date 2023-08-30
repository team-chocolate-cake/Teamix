package com.chocolate.viewmodel.organizationTasks

import com.chocolate.entities.uills.Empty

data class OrganizationTasksUiState(
    val organizationTitle: String = String.Empty,
    val imageUrl: String = String.Empty,
    val tasks: List<TasksUiState> = emptyList(),
    val isLoading: Boolean = true,
    val role: String = String.Empty,
    val error: String? = null
)

data class TasksUiState(
    val taskName: String = String.Empty,
    val fistSubTaskName: String = String.Empty,
    val secondSubTaskName: String = String.Empty,
    val isFirstSubTaskCompleted: Boolean = false,
    val isSecondSubTaskCompleted: Boolean = false,
    val taskDate: String = String.Empty,
    val taskProgress: Float = 0f,
)
