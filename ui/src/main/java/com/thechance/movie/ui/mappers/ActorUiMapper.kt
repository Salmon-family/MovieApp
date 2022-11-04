package com.thechance.movie.ui.mappers

import com.thechance.movie.ui.models.ActorUiState
import javax.inject.Inject

class ActorUiMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Actor, ActorUiState> {
    override fun map(input: com.devfalah.models.Actor): ActorUiState {
        return ActorUiState(
            input.actorID,
            input.actorName,
            input.actorImage,
        )
    }
}