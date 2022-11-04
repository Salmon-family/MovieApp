package com.karrar.movieapp.domain.usecases.movieDetails

import com.karrar.movieapp.domain.models.MovieDetails
import com.karrar.movieapp.domain.usecases.home.mappers.movie.WatchHistoryMapper
import com.thechance.repository.MovieRepository
import javax.inject.Inject

class InsertMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val watchHistoryMapper: WatchHistoryMapper
) {
    suspend operator fun invoke(movie: MovieDetails) {
        return movieRepository.insertMovie(watchHistoryMapper.map(movie))
    }
}