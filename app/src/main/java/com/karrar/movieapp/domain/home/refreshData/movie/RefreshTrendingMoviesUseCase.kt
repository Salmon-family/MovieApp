package com.karrar.movieapp.domain.home.refreshData.movie

import com.karrar.movieapp.data.local.mappers.movie.TrendingMovieMapper
import com.karrar.movieapp.data.repository.MovieRepository
import javax.inject.Inject

class RefreshTrendingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val trendingMovieMapper: TrendingMovieMapper,
){

    suspend operator fun invoke() {
        val items = movieRepository.getTrendingMovies(1).map(trendingMovieMapper::map)
        movieRepository.deleteTrendingMovies()
        movieRepository.insertTrendingMovies(items)

    }
}