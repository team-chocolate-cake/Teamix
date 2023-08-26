package com.chocolate.teamix.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
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
    private const val ENCRYPTED_SHARED_PREFERENCES = "encryptedSharedPreferencesFileName"

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

    @Singleton
    @Provides
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context,
        masterKey: MasterKey,
        @Named(ENCRYPTED_SHARED_PREFERENCES) encryptedSharedPreferencesFileName: String
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            encryptedSharedPreferencesFileName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Named(ENCRYPTED_SHARED_PREFERENCES)
    @Provides
    @Singleton
    fun provideEncryptedSharedPreferencesFileName(): String = "user_encrypted_file"

    @Singleton
    @Provides
    fun provideMasterKey(
        @ApplicationContext context: Context
    ): MasterKey {
        return MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    }

}