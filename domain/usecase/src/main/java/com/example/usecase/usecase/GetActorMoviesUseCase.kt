package com.example.usecase.usecase

import com.example.models.models.ActorMovie
import com.example.usecase.mappers.ActorMoviesMapper
import com.example.usecase.mappers.ListMapper
import javax.inject.Inject

class GetActorMoviesUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val actorMoviesMapper: ActorMoviesMapper
) {

    suspend operator fun invoke(actorId: Int): List<ActorMovie> {
        val response = movieRepository.getActorMovies(actorId = actorId)
        return ListMapper(actorMoviesMapper).mapList(response?.cast)
    }
}
