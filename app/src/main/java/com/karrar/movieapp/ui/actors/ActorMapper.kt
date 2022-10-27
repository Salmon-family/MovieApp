package com.karrar.movieapp.ui.actors

import com.karrar.movieapp.domain.mappers.Mapper
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.actors.models.ActorInfoUIState
import javax.inject.Inject

class ActorMapper @Inject constructor() : Mapper<Actor, ActorInfoUIState> {
    override fun map(input: Actor): ActorInfoUIState {
        return ActorInfoUIState(
            actorID = input.actorID,
            actorName = input.actorName,
            actorImage = input.actorImage
        )
    }
}