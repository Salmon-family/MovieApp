package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.karrar.movieapp.domain.usecases.mappers.Mapper
import com.karrar.movieapp.domain.models.Actor
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.SeriesCastUIState
import javax.inject.Inject

class SeriesCastUIMapper @Inject constructor() : Mapper<Actor, SeriesCastUIState> {
    override fun map(input: Actor): SeriesCastUIState {
        return SeriesCastUIState(
            actorID = input.actorID,
            actorName = input.actorName,
            actorImage = input.actorImage
        )
    }
}
