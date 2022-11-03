package com.karrar.movieapp.domain.usecases.movieDetails

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.movie.WatchHistoryMapper
import com.karrar.movieapp.domain.models.MovieDetails
import com.karrar.movieapp.domain.models.WatchHistory
import javax.inject.Inject

class InsertMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val watchHistoryMapper: WatchHistoryMapper
) {
    suspend operator fun invoke(movie: MovieDetails) {
        return movieRepository.insertMovie(watchHistoryMapper.map(movie))
    }
}