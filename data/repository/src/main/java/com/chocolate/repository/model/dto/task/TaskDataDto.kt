package com.chocolate.repository.model.dto.task

data class TaskDataDto(
    val id: Int = 0,
    val startDate: Long = 0L,
    val endDate: Long = 0L,
    val subTask: List<SubTaskDto> = emptyList(),
    val progress: Int = 0,
    val assignUser: List<String> = emptyList(),
    val title: String = "",
)