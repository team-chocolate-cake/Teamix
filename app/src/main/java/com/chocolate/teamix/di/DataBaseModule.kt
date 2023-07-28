package com.chocolate.teamix.di

import android.content.Context
import androidx.room.Room
import com.chocolate.local.dao.DraftDao
import com.chocolate.local.dao.StreamDao
import com.chocolate.local.dao.TrendDao
import com.chocolate.local.dao.UserDao
import com.chocolate.local.database.TeamixDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun providesMovieDatabase(
        @ApplicationContext context: Context,
    ): TeamixDataBase {
        return Room.databaseBuilder(
            context,
            TeamixDataBase::class.java,
            "TeamixDataBase.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDraftDao(teamixDataBase: TeamixDataBase): DraftDao {
        return teamixDataBase.draftDao
    }

    @Singleton
    @Provides
    fun provideStreamDao(teamixDataBase: TeamixDataBase): StreamDao {
        return teamixDataBase.streamDao
    }

    @Singleton
    @Provides
    fun provideTrendDao(teamixDataBase: TeamixDataBase): TrendDao {
        return teamixDataBase.trendDao
    }

    @Singleton
    @Provides
    fun provideUserDao(teamixDataBase: TeamixDataBase): UserDao {
        return teamixDataBase.userDao
    }
}