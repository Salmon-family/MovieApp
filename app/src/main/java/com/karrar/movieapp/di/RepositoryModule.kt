package com.karrar.movieapp.di

import com.karrar.movieapp.data.remote.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    abstract fun bindMovieRepository(movieRepositoryImp: MovieRepositoryImp): MovieRepository

    @Binds
    abstract fun bindSeriesRepository(seriesRepositoryImp: SeriesRepositoryImp): SeriesRepository

    @Binds
    abstract fun bindAccountRepository(accountRepositoryImp: AccountRepositoryImp): AccountRepository

}