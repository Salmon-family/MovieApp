package com.karrar.movieapp.di

import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.MovieRepositoryImp
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepositoryImp
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.remote.service.SeriesService
import com.karrar.movieapp.domain.mappers.ActorMapper
import com.karrar.movieapp.domain.mappers.GenreMapper
import com.karrar.movieapp.domain.mappers.MovieMapper
import com.karrar.movieapp.domain.mappers.SeriesMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    @Named("MovieRepository")
    fun provideMovieRepository(
        movieService: MovieService,
        movieMapper: MovieMapper,
        actorMapper: ActorMapper,
        genreMapper: GenreMapper
    ): MovieRepository {
        return MovieRepositoryImp(movieService, movieMapper, actorMapper, genreMapper)
    }

    @Provides
    @Singleton
    @Named("SeriesRepository")
    fun provideSeriesRepository(
        seriesService: SeriesService,
        seriesMapper: SeriesMapper
    ): SeriesRepository {
        return SeriesRepositoryImp(seriesService, seriesMapper)
    }

}