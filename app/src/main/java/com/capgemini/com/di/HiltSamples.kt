package com.capgemini.com.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

class AnalyticsAdapter @Inject constructor(
    @ActivityContext private val context: Context,
    val service: AnalyticsService
)


interface AnalyticsService {
    fun analyticsMethods(): String
}

interface AnalyticsService2 {
    fun analyticsMethods(): String
}

@Singleton
class AnalyticsServiceImpl @Inject constructor() : AnalyticsService {
    override fun analyticsMethods(): String {
        print("this is analyticsMethods ")
        return "this is analytics"
    }
}

@Module
@InstallIn(ActivityComponent::class)
abstract class AnalyticsModule {

    @Binds
    abstract fun bindAnalyticsService(analyticsServiceImpl: AnalyticsServiceImpl): AnalyticsService
}


@Module
@InstallIn(ActivityComponent::class)
object Analytics2Module {

    @Provides
    fun provideAnalyticsService(): AnalyticsService2 {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(AnalyticsService2::class.java)
    }
}

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        return chain.proceed(request)
    }
}

class OtherInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        return chain.proceed(request)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptorOkHttpClient

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @OtherInterceptorOkHttpClient
    @Provides
    fun provideOtherInterceptorOkHttpClient(
        otherInterceptor: OtherInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(otherInterceptor)
            .build()
    }
}


// As a dependency of another class.
@Module
@InstallIn(ActivityComponent::class)
object AnalyticsModule3 {

//    @Provides
//    fun provideAnalyticsService(@AuthInterceptorOkHttpClient okHttpClient: OkHttpClient): AnalyticsService {
//        return Retrofit.Builder()
//            .baseUrl("https://example.com")
//            .client(okHttpClient)
//            .build()
//            .create(AnalyticsService::class.java)
//    }
}

class ExampleServiceImpl @Inject constructor(
    @AuthInterceptorOkHttpClient private val okHttpClient: OkHttpClient
)

@AndroidEntryPoint
class ExampleActivity : AppCompatActivity() {
    @AuthInterceptorOkHttpClient
    @Inject
    lateinit var okHttpClient: OkHttpClient
}

/**
 *常用的component
 * ApplicationComponent	Application  Application#onCreate()	Application#onDestroy()  	@Singleton
 * ActivityRetainedComponent	ViewModel  Activity#onCreate()	Activity#onDestroy()  @ActivityRetainedScope
 * ActivityComponent	Activity  	Activity#onCreate()	Activity#onDestroy()  	@ActivityScoped
 * FragmentComponent	Fragment  	Fragment#onAttach()	Fragment#onDestroy()  	@FragmentScoped
 * ViewComponent	View  	View#super()	视图销毁时  	@ViewScoped
 * ViewWithFragmentComponent	带有 @WithFragmentBindings 注释的 View   	View#super()	视图销毁时  	@ViewScoped
 * ServiceComponent	Service  Service#onCreate()	Service#onDestroy()  	@ServiceScoped
 *
  */