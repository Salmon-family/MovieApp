package com.devfalah.usecases

import com.thechance.local.database.entity.WatchHistoryEntity
import com.thechance.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWatchHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(): Flow<List<WatchHistoryEntity>> {
        return movieRepository.getAllWatchedMovies()
    }
}