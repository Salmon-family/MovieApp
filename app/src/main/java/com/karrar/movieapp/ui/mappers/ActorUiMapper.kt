package com.karrar.movieapp.ui.mappers

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Actor
import com.karrar.movieapp.ui.models.ActorUiState
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