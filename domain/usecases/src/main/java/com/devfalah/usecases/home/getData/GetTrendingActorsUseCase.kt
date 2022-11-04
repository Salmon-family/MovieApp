package com.devfalah.usecases.home.getData

import com.devfalah.models.Actor
import com.devfalah.usecases.home.mappers.ActorEntityMapper
import com.thechance.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTrendingActorsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val actorMapper: ActorEntityMapper,
) {

  suspend operator fun invoke(): Flow<List<Actor>> {
        return movieRepository.getTrendingActors().map {
            it.map(actorMapper::map)
        }
    }
}