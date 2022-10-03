package com.karrar.movieapp.di

import com.karrar.movieapp.data.local.DataStorePreferences
import com.karrar.movieapp.data.remote.repository.AccountRepository
import com.karrar.movieapp.data.remote.repository.AccountRepositoryImp
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.MovieRepositoryImp
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.utilities.DataClassParser
import com.karrar.movieapp.domain.mappers.ActorDetailsMapper
import com.karrar.movieapp.domain.mappers.ActorMoviesMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieService: MovieService,
        actorDetailsMapper: ActorDetailsMapper,
        actorMoviesMapper: ActorMoviesMapper,
    ): MovieRepository {
        return MovieRepositoryImp(movieService, actorDetailsMapper, actorMoviesMapper)
    }

    @Singleton
    @Provides
    fun provideAccountRepository(
        movieService: MovieService,
        dataStorePreferences: DataStorePreferences,
        dataClassParser: DataClassParser,
    ): AccountRepository {
        return AccountRepositoryImp(movieService, dataStorePreferences, dataClassParser)
    }
}