package com.karrar.movieapp.di

import com.karrar.movieapp.data.local.DataStorePreferences
import com.karrar.movieapp.data.local.database.daos.MovieDao
import com.karrar.movieapp.data.remote.repository.AccountRepository
import com.karrar.movieapp.data.remote.repository.AccountRepositoryImp
import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.MovieRepositoryImp
import com.karrar.movieapp.data.remote.repository.*
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.*
import com.karrar.movieapp.data.remote.service.SeriesService
import com.karrar.movieapp.utilities.DataClassParser
import com.karrar.movieapp.domain.mappers.ActorDetailsMapper
import com.karrar.movieapp.domain.mappers.ActorMoviesMapper
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
        personMapper: PersonMapper,
        moviesMapper: MovieMapper,
        seriesMapper: SeriesMapper,
        actorDetailsMapper: ActorDetailsMapper,
        actorMoviesMapper: ActorMoviesMapper,
        movieDao: MovieDao,
        searchHistoryMapper: SearchHistoryMapper,
        actorMapper: ActorMapper,
        movieMapper: MediaMapper,
        genreMapper: GenreMapper,
        trendMapper: TrendMapper
    ): MovieRepository {
        return MovieRepositoryImp(
            movieService,
            actorDetailsMapper,
            actorMoviesMapper,
            actorMapper,
            genreMapper,
            movieMapper,
            personMapper,
            moviesMapper,
            seriesMapper,
            movieDao,
            searchHistoryMapper,
            trendMapper
        )
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