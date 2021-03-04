package com.capgemini.di

import android.content.Context
import androidx.room.Room
import com.capgemini.api.CommunityApi
import com.capgemini.api.GithubApi
import com.capgemini.database.SmartDataBase
import com.capgemini.http.BuildConfig
import com.capgemini.http.RetrofitManager
import com.capgemini.repository.SampleRepository1
import com.capgemini.repository.SampleRepository2
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
    fun provideSampleRepository1(dataBase: SmartDataBase,reposDetailsApi: GithubApi) = SampleRepository1(dataBase.simpleEntityDao(),reposDetailsApi)


    @Singleton
    @Provides
    fun provideSampleRepository2(communityApi: CommunityApi) = SampleRepository2(communityApi)


    @Singleton
    @Provides
    fun provideCommunityApi(retrofitManager: RetrofitManager) = retrofitManager.createApi(CommunityApi::class.java, BuildConfig.COMMUNITY_URL)


    @Singleton
    @Provides
    fun provideReposDetailsApi(retrofitManager: RetrofitManager) = retrofitManager.createApi(GithubApi::class.java, BuildConfig.GITHUB)


    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): SmartDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            SmartDataBase::class.java,
            "Tasks.db"
        ).build()
    }


    @Singleton
    @Provides
    fun provideRetrofitManager(@ApplicationContext context: Context): RetrofitManager {
        return RetrofitManager(context)
    }
}
