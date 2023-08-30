package com.chocolate.repository.mappers.task

import com.chocolate.entities.task.SubTask
import com.chocolate.entities.task.Task
import com.chocolate.repository.mappers.toDate
import com.chocolate.repository.model.dto.task.SubTaskDto
import com.chocolate.repository.model.dto.task.TaskDataDto
import java.util.Date

fun TaskDataDto.toEntity(): Task {
    return Task(
        id = id,
        startDate = startDate.toDate(),
        endDate = endDate.toDate(),
        subTask = subTask.toEntity(),
        progress = progress,
        assignUser = assignUser,
        title = title,

    )
}
fun SubTaskDto.toEntity():SubTask{
    return SubTask(
        id = id,
        title = title,
    )
}
fun List<SubTaskDto>.toEntity():List<SubTask>{
    return this.map { it.toEntity() }
}
