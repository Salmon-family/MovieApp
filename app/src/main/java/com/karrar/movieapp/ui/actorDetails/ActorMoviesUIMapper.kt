package com.karrar.movieapp.ui.actorDetails

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.ActorMovie
import javax.inject.Inject

class ActorMoviesUIMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.ActorMovie, ActorMoviesUIState> {
    override fun map(input: com.devfalah.models.ActorMovie): ActorMoviesUIState {
        return ActorMoviesUIState(
            id = input.movieId,
            imageUrl = input.movieImage
        )
    }
}