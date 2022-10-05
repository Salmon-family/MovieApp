package com.karrar.movieapp.di

import com.karrar.movieapp.data.remote.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideMovieRepository(
        movieRepositoryImp: MovieRepositoryImp
    ): MovieRepository

    @Singleton
    @Binds
    abstract fun provideSeriesRepository(
        seriesRepositoryImp: SeriesRepositoryImp
    ): SeriesRepository

    @Singleton
    @Binds
    abstract fun provideAccountRepository(
        accountRepositoryImp: AccountRepositoryImp
    ): AccountRepository
}