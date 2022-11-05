package com.thechance.viewmodel.tvShowDetails.tvShowUIMapper

import com.devfalah.models.Actor
import com.devfalah.usecases.mappers.Mapper
import com.thechance.viewmodel.tvShowDetails.tvShowUIState.SeriesCastUIState
import javax.inject.Inject

class SeriesCastUIMapper @Inject constructor() :
    Mapper<Actor, SeriesCastUIState> {
    override fun map(input: Actor): SeriesCastUIState {
        return SeriesCastUIState(
            actorID = input.actorID,
            actorName = input.actorName,
            actorImage = input.actorImage
        )
    }
}
