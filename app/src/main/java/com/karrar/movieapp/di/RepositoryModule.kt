package com.karrar.movieapp.di

import com.karrar.movieapp.data.local.DataStorePreferences
import com.karrar.movieapp.data.remote.repository.AccountRepository
import com.karrar.movieapp.data.remote.repository.AccountRepositoryImp
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.MovieRepositoryImp
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepositoryImp
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.utilities.DataClassParser
import com.karrar.movieapp.domain.mappers.ActorDetailsMapper
import com.karrar.movieapp.domain.mappers.ActorMoviesMapper
import com.karrar.movieapp.domain.mappers.ActorMapper
import com.karrar.movieapp.data.remote.service.SeriesService
import com.karrar.movieapp.domain.mappers.ActorMapper
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.MediaMapper
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
        actorMapper: ActorMapper,
        movieMapper: MediaMapper,
        genreMapper: GenreMapper
    ): MovieRepository {
        return MovieRepositoryImp(movieService, actorDetailsMapper, actorMoviesMapper, actorMapper)
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