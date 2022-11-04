package com.karrar.movieapp.domain.usecases

import com.karrar.movieapp.domain.models.ActorDetails
import com.karrar.movieapp.domain.usecases.home.mappers.MovieMappersContainer
import javax.inject.Inject

class GetActorDetailsUseCase @Inject constructor(
    private val movieRepository: com.thechance.repository.MovieRepository,
    private val movieMappersContainer: MovieMappersContainer,
) {
    suspend operator fun invoke(actorId: Int): ActorDetails {
        val response = movieRepository.getActorDetails(actorId = actorId)
        return if (response != null) {
            movieMappersContainer.actorDetailsMapper.map(response)
        } else {
            throw Throwable("Not Success")
        }
    }
}
