package com.example.ajaynewsapp.di

import com.example.ajaynewsapp.data.repository.TopHeadlinesRepositoryImpl
import com.example.ajaynewsapp.domain.repository.TopHeadlinesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindTopHeadlinesRepository(impl: TopHeadlinesRepositoryImpl): TopHeadlinesRepository

}