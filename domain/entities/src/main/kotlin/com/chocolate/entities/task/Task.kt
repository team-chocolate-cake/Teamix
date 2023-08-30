package com.chocolate.entities.task

import java.util.Date
data class Task(
    val id: Int,
    val startDate: Date,
    val endDate: Date,
    val subTask: List<SubTask>,
    val progress: Int,
    val assignUser: List<String>,
    val title: String,
)