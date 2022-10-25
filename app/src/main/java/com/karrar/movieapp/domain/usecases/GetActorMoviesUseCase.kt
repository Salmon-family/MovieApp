package com.karrar.movieapp.domain.usecases

import com.karrar.movieapp.BuildConfig
import com.karrar.movieapp.data.remote.response.MovieDto
import com.karrar.movieapp.data.repository.MovieRepository
import com.karrar.movieapp.ui.actorDetails.ActorMoviesUIState
import javax.inject.Inject

class GetActorMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(actorId: Int) = movieRepository.getActorMovies(actorId).map {
        it.toActorMoviesUIState()
    }

    private fun MovieDto.toActorMoviesUIState(): ActorMoviesUIState {
        return ActorMoviesUIState(
            this.id ?: 0,
            BuildConfig.IMAGE_BASE_PATH + this.posterPath,
        )
    }
}