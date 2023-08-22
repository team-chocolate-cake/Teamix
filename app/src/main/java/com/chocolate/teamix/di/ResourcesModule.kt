package com.chocolate.teamix.di

import com.chocolate.presentation.util.StringsResourceImpl
import com.chocolate.viewmodel.base.StringsResource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourcesModule {
    @Binds
    @Singleton
    abstract fun bindStringsResource(stringsResource: StringsResourceImpl): StringsResource

}