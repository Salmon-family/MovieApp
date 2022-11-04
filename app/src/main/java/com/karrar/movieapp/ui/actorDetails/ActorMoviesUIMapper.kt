package com.karrar.movieapp.ui.actorDetails

import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.ActorMovie
import javax.inject.Inject

class ActorMoviesUIMapper @Inject constructor() : Mapper<ActorMovie, ActorMoviesUIState> {
    override fun map(input: ActorMovie): ActorMoviesUIState {
        return ActorMoviesUIState(
            id = input.movieId,
            imageUrl = input.movieImage
        )
    }
}