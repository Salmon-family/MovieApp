package com.karrar.movieapp.domain.home.refreshData.movie

import com.karrar.movieapp.data.local.mappers.movie.UpcomingMovieMapper
import com.karrar.movieapp.data.repository.MovieRepository
import javax.inject.Inject

class RefreshUpcomingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: UpcomingMovieMapper
){

    suspend operator fun invoke() {
        val items = movieRepository.getUpcomingMovies(1).map(movieMapper::map)
        movieRepository.deleteUpcomingMovies()
        movieRepository.insertUpcomingMovies(items)
    }

}