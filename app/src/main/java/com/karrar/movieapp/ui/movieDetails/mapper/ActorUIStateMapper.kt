package com.karrar.movieapp.ui.movieDetails.mapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Actor
import com.karrar.movieapp.ui.models.ActorUiState
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