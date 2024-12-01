package com.app.android_test.core.di

import com.app.android_test.data.repository.news.NewsRepository
import com.app.android_test.data.repository.news.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl,
    ): NewsRepository
}