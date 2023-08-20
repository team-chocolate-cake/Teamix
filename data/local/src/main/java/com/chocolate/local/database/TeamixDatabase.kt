package com.chocolate.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chocolate.local.dao.TeamixDao
import com.chocolate.repository.model.localDto.stream.StreamLocalDto
import com.chocolate.repository.model.localDto.users.UserLocalDto

@Database(
    entities = [
        UserLocalDto::class,
        StreamLocalDto::class,
    ], version = 1
)
abstract class TeamixDatabase : RoomDatabase() {
    abstract val teamixDao: TeamixDao
}