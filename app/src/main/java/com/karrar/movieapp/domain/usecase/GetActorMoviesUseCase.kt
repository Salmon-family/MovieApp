package com.karrar.movieapp.domain.usecase

import com.thechance.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.ActorMoviesMapper
import com.karrar.movieapp.domain.mappers.ListMapper
import com.karrar.movieapp.domain.models.ActorMovie
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