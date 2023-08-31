package com.chocolate.teamix.di

import com.chocolate.remote.firebase.FireBaseDataSource
import com.chocolate.repository.datastore.realtime.RealTimeDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RealTimeDataSource {
    @Singleton
    @Binds
   abstract fun bindFireBaseDataSource(fireBaseDataSource: FireBaseDataSource):RealTimeDataSource
}