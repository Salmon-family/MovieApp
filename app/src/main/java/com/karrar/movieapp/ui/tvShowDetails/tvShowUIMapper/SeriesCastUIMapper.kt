package com.karrar.movieapp.ui.tvShowDetails.tvShowUIMapper

import com.devfalah.usecases.mappers.Mapper
import com.devfalah.models.Actor
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.SeriesCastUIState
import javax.inject.Inject

class SeriesCastUIMapper @Inject constructor() :
    com.devfalah.usecases.mappers.Mapper<com.devfalah.models.Actor, SeriesCastUIState> {
    override fun map(input: com.devfalah.models.Actor): SeriesCastUIState {
        return SeriesCastUIState(
            actorID = input.actorID,
            actorName = input.actorName,
            actorImage = input.actorImage
        )
    }
}
