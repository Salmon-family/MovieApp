package com.karrar.movieapp.ui.movieDetails.mapper

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.models.ActorUiState
import javax.inject.Inject

class ActorUIStateMapper @Inject constructor() : Mapper<Actor, ActorUiState> {
    override fun map(input: Actor): ActorUiState {
        return ActorUiState(
            id = input.actorID,
            imageUrl = input.actorImage,
            name = input.actorName
        )
    }
}