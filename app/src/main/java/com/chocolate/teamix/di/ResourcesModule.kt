package com.chocolate.teamix.di

import com.chocolate.presentation.util.StringsResourceImpl
import com.chocolate.viewmodel.base.StringsResource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ResourcesModule {
    @Binds
    @ViewModelScoped
    abstract fun bindStringsResource(stringsResource: StringsResourceImpl): StringsResource

}