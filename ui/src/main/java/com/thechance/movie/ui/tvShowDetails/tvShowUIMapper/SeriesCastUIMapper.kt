package com.thechance.movie.ui.tvShowDetails.tvShowUIMapper

import com.thechance.movie.ui.tvShowDetails.tvShowUIState.SeriesCastUIState
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
