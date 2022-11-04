package com.devfalah.usecases

import com.devfalah.models.ActorMovie
import com.devfalah.usecases.home.mappers.ActorMoviesMapper
import com.devfalah.usecases.home.mappers.ListMapper
import javax.inject.Inject

class GetActorMoviesUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val actorMoviesMapper: ActorMoviesMapper,
) {

    suspend operator fun invoke(actorId: Int): List<ActorMovie> {
        val response = movieRepository.getActorMovies(actorId = actorId)
        return ListMapper(actorMoviesMapper).mapList(response?.cast)
    }
}