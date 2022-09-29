package com.karrar.movieapp.di

import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.MovieRepositoryImp
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.domain.mappers.CastMapper
import com.karrar.movieapp.domain.mappers.MovieDetailsMapper
import com.karrar.movieapp.domain.mappers.MovieMapper
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
        castMapper: CastMapper,
        movieDetailsMapper: MovieDetailsMapper,
        movieMapper: MovieMapper
    ): MovieRepository {
        return MovieRepositoryImp(
            movieService,
            castMapper,
            movieDetailsMapper,
            movieMapper
        )
    }

    @Provides
    fun provideCastMapper() = CastMapper()

    @Provides
    fun provideMovieDetailsMapper() = MovieDetailsMapper()

    @Provides
    fun provideMovieMapper() = MovieMapper()

}