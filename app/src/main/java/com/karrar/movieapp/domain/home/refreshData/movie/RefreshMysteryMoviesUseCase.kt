package com.karrar.movieapp.domain.home.refreshData.movie

import com.karrar.movieapp.data.local.mappers.movie.MysteryMovieMapper
import com.karrar.movieapp.data.repository.MovieRepository
import javax.inject.Inject

class RefreshMysteryMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MysteryMovieMapper
){

    suspend operator fun invoke() {
        val items = movieRepository.getMysteryMovies(1).map(movieMapper::map)
        movieRepository.deleteMysteryMovies()
        movieRepository.insertMysteryMovies(items)
    }

}