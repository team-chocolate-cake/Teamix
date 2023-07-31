package com.chocolate.local.entities.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "story_table")
data class StoriesEntity(
    @PrimaryKey
    val id: String,
    val image: Int
)