package com.karrar.movieapp.ui.mappers

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.models.ActorUiState
import javax.inject.Inject

class ActorUiMapper @Inject constructor() : Mapper<Actor, ActorUiState> {
    override fun map(input: Actor): ActorUiState {
        return ActorUiState(
            input.actorID,
            input.actorName,
            input.actorImage,
        )
    }
}