package com.karrar.movieapp.domain.home.refreshData.movie

import com.karrar.movieapp.data.local.mappers.movie.AdventureMovieMapper
import com.karrar.movieapp.data.repository.MovieRepository
import javax.inject.Inject

class RefreshAdventureMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: AdventureMovieMapper
){

    suspend operator fun invoke() {
        val items = movieRepository.getAdventureMovies(1).map(movieMapper::map)
        movieRepository.deleteAdventureMovies()
        movieRepository.insertAdventureMovies(items)
    }

}