package com.chocolate.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chocolate.local.dao.DraftMessagesDao
import com.chocolate.local.dao.OrganizationsDao
import com.chocolate.local.dao.StreamDao
import com.chocolate.local.dao.TrendDao
import com.chocolate.local.dao.UserDao
import com.chocolate.repository.dto.local.draft.DraftLocalDto
import com.chocolate.repository.dto.local.stream.StreamLocalDto
import com.chocolate.repository.dto.local.trends.TrendsLocalDto
import com.chocolate.repository.dto.local.users.ChatLocalDto
import com.chocolate.repository.dto.local.users.GroupsLocalDto
import com.chocolate.repository.dto.local.users.OrganizationsLocalDto
import com.chocolate.repository.dto.local.users.StoriesLocalDto


@Database(
    entities = [
        StoriesLocalDto::class,
        DraftLocalDto::class,
        StreamLocalDto::class,
        TrendsLocalDto::class,
        GroupsLocalDto::class,
        ChatLocalDto::class,
        OrganizationsLocalDto::class], version = 1
)
abstract class TeamixDatabase : RoomDatabase() {
    abstract val draftMessagesDao: DraftMessagesDao
    abstract val streamDao: StreamDao
    abstract val trendDao: TrendDao
    abstract val userDao: UserDao
    abstract val organizationsDao: OrganizationsDao
}