package com.karrar.movieapp.domain.usecases

import com.karrar.movieapp.domain.models.ActorMovie
import com.karrar.movieapp.domain.usecases.home.mappers.ActorMoviesMapper
import com.karrar.movieapp.domain.usecases.home.mappers.ListMapper
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