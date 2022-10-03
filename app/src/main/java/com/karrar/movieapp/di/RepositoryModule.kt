package com.karrar.movieapp.di

import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.MovieRepositoryImp
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepositoryImp
import com.karrar.movieapp.data.remote.service.MovieService
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

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieService: MovieService,
        movieMapper: MediaMapper,
        actorMapper: ActorMapper,
        genreMapper: GenreMapper
    ): MovieRepository {
        return MovieRepositoryImp(movieService, movieMapper, actorMapper, genreMapper)
    }

    @Provides
    @Singleton
    fun provideSeriesRepository(
        seriesService: SeriesService,
        seriesMapper: MediaMapper,
        genreMapper: GenreMapper
    ): SeriesRepository {
        return SeriesRepositoryImp(seriesService, seriesMapper, genreMapper)
    }

}