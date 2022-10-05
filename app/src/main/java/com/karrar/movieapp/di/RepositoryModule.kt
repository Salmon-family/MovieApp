package com.karrar.movieapp.di

import com.karrar.movieapp.data.local.DataStorePreferences
import com.karrar.movieapp.data.remote.repository.*
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.remote.service.SeriesService
import com.karrar.movieapp.domain.mappers.*
import com.karrar.movieapp.utilities.DataClassParser
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
        genreMapper: GenreMapper,
        accountMapper: AccountMapper,
        ratedMoviesMapper: RatedMoviesMapper
    ): MovieRepository {
        return MovieRepositoryImp(movieService, actorDetailsMapper, actorMoviesMapper, actorMapper, genreMapper, movieMapper, accountMapper, ratedMoviesMapper)
    }

    @Provides
    @Singleton
    fun provideSeriesRepository(
        seriesService: SeriesService,
        seriesMapper: MediaMapper
    ): SeriesRepository {
        return SeriesRepositoryImp(seriesService, seriesMapper)
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