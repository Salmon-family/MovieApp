package com.karrar.movieapp.domain.usecase

import com.thechance.repository.MovieRepository
import com.karrar.movieapp.domain.mappers.MovieMappersContainer
import com.karrar.movieapp.domain.models.ActorDetails
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
