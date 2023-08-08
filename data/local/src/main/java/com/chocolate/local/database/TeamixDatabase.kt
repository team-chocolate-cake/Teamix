package com.chocolate.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chocolate.local.dao.DraftMessagesDao
import com.chocolate.local.dao.StreamDao
import com.chocolate.local.dao.TrendDao
import com.chocolate.local.dao.UserDao
import com.chocolate.repository.dto.local.draft.DraftEntity
import com.chocolate.repository.dto.local.stream.StreamEntity
import com.chocolate.repository.dto.local.trends.TrendsEntity
import com.chocolate.repository.dto.local.users.ChatEntity
import com.chocolate.repository.dto.local.users.GroupsEntity
import com.chocolate.repository.dto.local.users.StoriesEntity


@Database(entities = [
    StoriesEntity::class,
    DraftEntity::class,
    StreamEntity::class,
    TrendsEntity::class,
    GroupsEntity::class,
    ChatEntity::class], version = 1)
abstract class TeamixDatabase : RoomDatabase() {
    abstract val draftMessagesDao: DraftMessagesDao
    abstract val streamDao: StreamDao
    abstract val trendDao: TrendDao
    abstract val userDao: UserDao
}