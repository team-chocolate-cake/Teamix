package com.chocolate.repository.model.localDto.draft

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "draft_table")
data class DraftLocalDto(
    @PrimaryKey
    val id: String,
    val progress: Int
)
