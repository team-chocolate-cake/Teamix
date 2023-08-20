package com.chocolate.teamix.di

import android.content.Context
import androidx.room.Room
import com.chocolate.local.dao.TeamixDao
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
    fun provideTeamixDao(teamixDatabase: TeamixDatabase): TeamixDao {
        return teamixDatabase.teamixDao
    }

}