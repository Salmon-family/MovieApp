package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.movie.WatchHistoryMapper
import com.karrar.movieapp.domain.models.WatchHistory
import javax.inject.Inject

class InsertMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val watchHistoryMapper: WatchHistoryMapper
) {
    suspend operator fun invoke(movie: WatchHistory) {
        return movieRepository.insertMovie(watchHistoryMapper.map(movie))
    }
}