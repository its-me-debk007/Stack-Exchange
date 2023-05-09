package com.example.stackexchangetask.data.di

import android.content.Context
import com.example.stackexchangetask.BuildConfig
import com.example.stackexchangetask.common.CACHE_SIZE
import com.example.stackexchangetask.common.HAS_NETWORK
import com.example.stackexchangetask.data.network.ApiService
import com.example.stackexchangetask.data.repository.RepositoryImpl
import com.example.stackexchangetask.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository = RepositoryImpl(apiService)

    @Singleton
    @Provides
    fun providesOkHttpClient(@ApplicationContext context: Context) = OkHttpClient.Builder()
        .cache(Cache(context.cacheDir, CACHE_SIZE))
        .addInterceptor { chain ->
            var request = chain.request()

            request = if (HAS_NETWORK(context)!!)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()

            chain.proceed(request)
        }
        .build()
}
