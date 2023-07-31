package com.chocolate.teamix.di

import android.content.Context
import androidx.room.Room
import com.chocolate.local.dao.DraftMessagesDao
import com.chocolate.local.dao.StreamDao
import com.chocolate.local.dao.TrendDao
import com.chocolate.local.dao.UserDao
import com.chocolate.local.database.TeamixDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTeamixDatabase(
        @ApplicationContext context: Context,
    ): TeamixDatabase {
        return Room.databaseBuilder(
            context,
            TeamixDatabase::class.java,
            "TeamixDatabase.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDraftDao(teamixDatabase: TeamixDatabase): DraftMessagesDao {
        return teamixDatabase.draftMessagesDao
    }

    @Singleton
    @Provides
    fun provideStreamDao(teamixDatabase: TeamixDatabase): StreamDao {
        return teamixDatabase.streamDao
    }

    @Singleton
    @Provides
    fun provideTrendDao(teamixDatabase: TeamixDatabase): TrendDao {
        return teamixDatabase.trendDao
    }

    @Singleton
    @Provides
    fun provideUserDao(teamixDatabase: TeamixDatabase): UserDao {
        return teamixDatabase.userDao
    }
}