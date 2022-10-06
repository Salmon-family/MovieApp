package com.karrar.movieapp.di

import com.karrar.movieapp.data.remote.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun provideMovieRepository(
        movieRepositoryImp: MovieRepositoryImp
    ): MovieRepository

    @ViewModelScoped
    @Binds
    abstract fun provideSeriesRepository(
        seriesRepositoryImp: SeriesRepositoryImp
    ): SeriesRepository

    @ViewModelScoped
    @Binds
    abstract fun provideAccountRepository(
        accountRepositoryImp: AccountRepositoryImp
    ): AccountRepository
}