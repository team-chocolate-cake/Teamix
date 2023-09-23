package com.chocolate.teamix.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {
    private const val DATASTORE_FILE_NAME = "dataStoreFileName"

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext applicationContext: Context,
        @Named(DATASTORE_FILE_NAME) dataStoreFileName: String
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            applicationContext.preferencesDataStoreFile(dataStoreFileName)
        }
    }

    @Named(DATASTORE_FILE_NAME)
    @Provides
    @Singleton
    fun provideDataStoreFileName(): String = "AppPrefStorage"
}