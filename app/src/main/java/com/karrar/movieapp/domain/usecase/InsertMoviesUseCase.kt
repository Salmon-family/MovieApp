package com.karrar.movieapp.domain.usecase

import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.MovieMappersContainer
import com.karrar.movieapp.domain.models.WatchHistory
import javax.inject.Inject

class InsertMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMappersContainer: MovieMappersContainer
) {
    suspend operator fun invoke(movie: WatchHistory) {
        return movieRepository.insertMovie(movieMappersContainer.watchHistoryMapper.map(movie))
    }
}