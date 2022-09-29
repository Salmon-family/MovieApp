package com.karrar.movieapp.di

import com.karrar.movieapp.data.remote.repository.MovieRepository
import com.karrar.movieapp.data.remote.repository.MovieRepositoryImp
import com.karrar.movieapp.data.remote.repository.SeriesRepository
import com.karrar.movieapp.data.remote.repository.SeriesRepositoryImp
import com.karrar.movieapp.data.remote.service.MovieService
import com.karrar.movieapp.data.remote.service.SeriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    @Named("MovieRepository")
    fun provideMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepositoryImp(movieService)
    }

    @Provides
    @Singleton
    @Named("SeriesRepository")
    fun provideSeriesRepository(seriesService: SeriesService): SeriesRepository {
        return SeriesRepositoryImp(seriesService)
    }
}