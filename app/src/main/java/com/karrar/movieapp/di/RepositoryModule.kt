package com.karrar.movieapp.di

import com.karrar.movieapp.data.local.DataStorePreferences
import com.karrar.movieapp.data.remote.repository.*
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.remote.service.SeriesService
import com.karrar.movieapp.utilities.DataClassParser
import com.karrar.movieapp.domain.mappers.ActorDetailsMapper
import com.karrar.movieapp.domain.mappers.ActorMoviesMapper
import com.karrar.movieapp.domain.mappers.ActorMapper
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.MediaMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
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