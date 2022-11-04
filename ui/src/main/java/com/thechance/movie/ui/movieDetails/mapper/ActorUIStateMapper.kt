package com.thechance.movie.ui.movieDetails.mapper

import com.thechance.movie.ui.models.ActorUiState
import javax.inject.Inject

class ActorUIStateMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Actor, ActorUiState> {
    override fun map(input: com.devfalah.models.Actor): ActorUiState {
        return ActorUiState(
            id = input.actorID,
            imageUrl = input.actorImage,
            name = input.actorName
        )
    }
}