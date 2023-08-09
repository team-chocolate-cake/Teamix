package com.chocolate.teamix.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.chocolate.local.dao.draft.DraftMessagesDao
import com.chocolate.local.dao.organization.OrganizationsDao
import com.chocolate.local.dao.stream.StreamDao
import com.chocolate.local.dao.trend.TrendDao
import com.chocolate.local.dao.user.UserDao
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

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create() {
            applicationContext.preferencesDataStoreFile("AppPrefStorage")
        }
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
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context,
        masterKey: MasterKey
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            "user_encrypted_file",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Singleton
    @Provides
    fun provideMasterKey(
        @ApplicationContext context: Context
    ): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

}