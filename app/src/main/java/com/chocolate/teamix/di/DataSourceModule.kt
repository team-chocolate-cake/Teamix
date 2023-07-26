package com.chocolate.teamix.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    /*@Binds
        fun bindLocalDataSource(localDataSourceImp: LocalDataSource): LocalDataSource

        @Binds
        fun bindRemoteDataSource(remoteDataSourceImp: RemoteDataSource): RemoteDataSource*/

}