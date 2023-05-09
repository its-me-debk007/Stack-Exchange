package com.example.stackexchangetask.data.di

import com.example.stackexchangetask.BuildConfig
import com.example.stackexchangetask.data.network.ApiService
import com.example.stackexchangetask.data.repository.RepositoryImpl
import com.example.stackexchangetask.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository = RepositoryImpl(apiService)
}
