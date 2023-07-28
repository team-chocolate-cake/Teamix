package com.chocolate.local.entities.draft

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "draft_table")
data class DraftEntity(@PrimaryKey
                       val id: String,val progress :Int)
