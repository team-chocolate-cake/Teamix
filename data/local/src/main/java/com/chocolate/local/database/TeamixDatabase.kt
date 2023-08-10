package com.chocolate.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chocolate.local.dao.draft.DraftMessagesDao
import com.chocolate.local.dao.organization.OrganizationsDao
import com.chocolate.local.dao.stream.StreamDao
import com.chocolate.local.dao.trend.TrendDao
import com.chocolate.local.dao.user.UserDao
import com.chocolate.repository.model.localDto.draft.DraftLocalDto
import com.chocolate.repository.model.localDto.stream.StreamLocalDto
import com.chocolate.repository.model.localDto.trends.TrendsLocalDto
import com.chocolate.repository.model.localDto.users.ChatLocalDto
import com.chocolate.repository.model.localDto.users.GroupsLocalDto
import com.chocolate.repository.model.localDto.users.OrganizationsLocalDto
import com.chocolate.repository.model.localDto.users.StoriesLocalDto

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