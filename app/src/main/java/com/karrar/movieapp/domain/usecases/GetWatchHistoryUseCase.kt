package com.karrar.movieapp.domain.usecases

import com.thechance.local.database.entity.WatchHistoryEntity
import com.thechance.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWatchHistoryUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
) {
    operator fun invoke(): Flow<List<com.thechance.local.database.entity.WatchHistoryEntity>> {
        return movieRepository.getAllWatchedMovies()
    }
}