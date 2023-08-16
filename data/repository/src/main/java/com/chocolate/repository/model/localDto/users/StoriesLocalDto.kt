package com.chocolate.repository.model.localDto.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "story_table")
data class StoriesLocalDto(
    @PrimaryKey
    val id: String,
    val image: Int
)