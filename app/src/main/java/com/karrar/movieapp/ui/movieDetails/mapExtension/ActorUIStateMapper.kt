package com.karrar.movieapp.ui.movieDetails.mapExtension

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.movieDetails.movieDetailsUIState.ActorUiState
import javax.inject.Inject

class ActorUIStateMapper @Inject constructor() : Mapper<Actor, ActorUiState> {
    override fun map(input: Actor): ActorUiState {
        return ActorUiState(
            actorID = input.actorID,
            actorImage = input.actorImage,
            actorName = input.actorName
        )
    }
}