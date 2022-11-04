package com.devfalah.usecases

import com.devfalah.models.ActorDetails
import com.devfalah.usecases.home.mappers.MovieMappersContainer
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
