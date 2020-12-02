package com.capgemini.module_sample

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityComponent::class)
class SampleModule {
    @IntoSet
    @Provides
    fun providerString():String{
        return "sample"
    }
}