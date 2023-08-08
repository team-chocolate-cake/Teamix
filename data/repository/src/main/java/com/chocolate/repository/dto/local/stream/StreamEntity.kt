package com.chocolate.repository.dto.local.stream

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stream_table")
data class StreamEntity(
    @PrimaryKey
    val id: String,
    val name: Int
)
