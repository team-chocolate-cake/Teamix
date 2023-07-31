package com.chocolate.local.entities.trends

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trend_table")
data class TrendsEntity(
    @PrimaryKey
    val id: String,
    val name: Int
)
