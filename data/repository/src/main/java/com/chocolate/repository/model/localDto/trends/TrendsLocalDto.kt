package com.chocolate.repository.model.localDto.trends

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trend_table")
data class TrendsLocalDto(
    @PrimaryKey
    val id: String,
    val name: Int
)
