package com.example.stackexchangetask.domain.di

import com.example.stackexchangetask.domain.repository.Repository
import com.example.stackexchangetask.domain.usecase.GetQuestionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideUseCase(repository: Repository): GetQuestionsUseCase = GetQuestionsUseCase(repository)
}