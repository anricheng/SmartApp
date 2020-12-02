package com.capgemini.module_profile

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityComponent::class)
class ProfileModule {
    @IntoSet
    @Provides
    fun  provideString():String{
        return "profile"
    }
}