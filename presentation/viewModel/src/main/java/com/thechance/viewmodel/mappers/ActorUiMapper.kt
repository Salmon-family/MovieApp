package com.thechance.viewmodel.mappers

import com.devfalah.models.Actor
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.models.ActorUiState
import javax.inject.Inject

class ActorUiMapper @Inject constructor() :
    Mapper<Actor, ActorUiState> {
    override fun map(input: Actor): ActorUiState {
        return ActorUiState(
            input.actorID,
            input.actorName,
            input.actorImage,
        )
    }
}