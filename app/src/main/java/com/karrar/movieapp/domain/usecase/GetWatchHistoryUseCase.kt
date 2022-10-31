package com.karrar.movieapp.domain.usecase

import com.thechance.repository.MovieRepository
import com.thechance.repository.local.database.entity.WatchHistoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWatchHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(): Flow<List<WatchHistoryEntity>> {
        return movieRepository.getAllWatchedMovies()
    }
}