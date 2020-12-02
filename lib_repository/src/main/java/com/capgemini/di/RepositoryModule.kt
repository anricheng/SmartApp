package com.capgemini.di

import android.content.Context
import androidx.room.Room
import com.capgemini.database.SmartDataBase
import com.capgemini.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(  dataBase: SmartDataBase) = MainRepository(dataBase.taskDao())


    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): SmartDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            SmartDataBase::class.java,
            "Tasks.db"
        ).build()
    }
}
