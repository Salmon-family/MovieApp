package com.karrar.movieapp.domain.home.refreshData.movie

import com.karrar.movieapp.data.local.mappers.movie.NowStreamingMovieMapper
import com.karrar.movieapp.data.repository.MovieRepository
import javax.inject.Inject

class RefreshNowStreamingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: NowStreamingMovieMapper,
) {

    suspend operator fun invoke() {
        val items = movieRepository.getNowStreamingMovies(1).map(movieMapper::map)
        movieRepository.deleteNowStreamingMovies()
        movieRepository.insertNowStreamingMovies(items)
    }

}