package com.chocolate.teamix.di

import android.content.Context
import androidx.room.Room
import com.chocolate.local.dao.draft.DraftMessagesDao
import com.chocolate.local.dao.draft.DraftMessagesLocalDataSourceImpl
import com.chocolate.local.dao.organization.OrganizationsDao
import com.chocolate.local.dao.organization.OrganizationsLocalDataSourceImpl
import com.chocolate.local.dao.stream.StreamDao
import com.chocolate.local.dao.stream.StreamLocalDataSourceImpl
import com.chocolate.local.dao.trend.TrendDao
import com.chocolate.local.dao.trend.TrendLocalDataSourceImpl
import com.chocolate.local.dao.user.UserDao
import com.chocolate.local.dao.user.UserLocalDataSourceImpl
import com.chocolate.local.database.TeamixDatabase
import com.chocolate.remote.drafts.DraftsMessagesImpl
import com.chocolate.repository.service.local.DraftMessagesLocalDataSource
import com.chocolate.repository.service.local.OrganizationsLocalDataSource
import com.chocolate.repository.service.local.StreamLocalDataSource
import com.chocolate.repository.service.local.TrendLocalDataSource
import com.chocolate.repository.service.local.UserLocalDataSource
import com.chocolate.repository.service.remote.DraftMessageDataSource
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

    @Singleton
    @Provides
    fun provideOrganizationsDao(teamixDatabase: TeamixDatabase): OrganizationsDao {
        return teamixDatabase.organizationsDao
    }

    @Singleton
    @Provides
    fun provideDraftMessageDataSource(draftMessagesDao: DraftMessagesDao): DraftMessagesLocalDataSource {
        return DraftMessagesLocalDataSourceImpl(draftMessagesDao)
    }

    @Singleton
    @Provides
    fun provideOrganizationsDataSource(organizationsDao: OrganizationsDao): OrganizationsLocalDataSource {
        return OrganizationsLocalDataSourceImpl(organizationsDao)
    }

    @Singleton
    @Provides
    fun provideStreamDataSource(streamDao: StreamDao): StreamLocalDataSource {
        return StreamLocalDataSourceImpl(streamDao)
    }

    @Singleton
    @Provides
    fun provideTrendDataSource(trendDao: TrendDao): TrendLocalDataSource {
        return TrendLocalDataSourceImpl(trendDao)
    }
    @Singleton
    @Provides
    fun provideUserDataSource(userDao: UserDao): UserLocalDataSource {
        return UserLocalDataSourceImpl(userDao)
    }
}