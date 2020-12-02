package com.capgemini.lib_common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @IntoSet
    @Provides
    fun provideString():String{
        return "app"
    }
}