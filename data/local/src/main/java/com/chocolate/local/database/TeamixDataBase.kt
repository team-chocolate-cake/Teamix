package com.chocolate.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chocolate.local.dao.DraftDao
import com.chocolate.local.dao.StreamDao
import com.chocolate.local.dao.TrendDao
import com.chocolate.local.dao.UserDao
import com.chocolate.local.entities.draft.DraftEntity
import com.chocolate.local.entities.stream.StreamEntity
import com.chocolate.local.entities.trends.TrendsEntity
import com.chocolate.local.entities.users.ChatEntity
import com.chocolate.local.entities.users.GroupsEntity
import com.chocolate.local.entities.users.StoriesEntity


@Database(entities = [
    StoriesEntity::class,
    DraftEntity::class,
    StreamEntity::class,
    TrendsEntity::class,
    GroupsEntity::class,
    ChatEntity::class], version = 1)
abstract class TeamixDataBase : RoomDatabase() {
    abstract fun draftDao(): DraftDao
    abstract fun streamDao(): StreamDao
    abstract fun trendDao(): TrendDao
    abstract fun userDao(): UserDao
}